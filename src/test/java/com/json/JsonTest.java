package com.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(JsonController.class)
public class JsonTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlight() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)))
                .andExpect(jsonPath("$.tickets.length()", is(1)));
    }

    @Test
    public void testFlights() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)))
                .andExpect(jsonPath("$.tickets[1].passenger.FirstName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[1].price", is(400)))
                .andExpect(jsonPath("$.tickets.length()", is(2)));
    }

    @Test
    public void testPostFlightString() throws Exception {
        String requestString = "flight: {\"tickets\": "
                + "[ { \"passenger\": "
                + "{ \"firstName\": \"Some name\", \"lastName\": \"Some other name\"},\"price\": 200 }, "
                + "{ \"passenger\": "
                + "{ \"FirstName\": \"Name B\", \"LastName\": \"Name C\"},\"price\": 150}] }";
        RequestBuilder request =
                post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[ { \"passenger\": { \"firstName\": \"Some name\", \"lastName\": \"Some other name\"},\"price\": \"200\" }, { \"passenger\": { \"firstName\": \"Name B\", \"lastName\": \"Name C\"},\"price\": \"150\" } ]");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testGson() throws Exception {
        JsonObject person = new JsonObject();
        person.addProperty("firstName", "Eliza");
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(person);
        MockHttpServletRequestBuilder request = post("/some/path")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"firstName\":\"Eliza\"}"));
    }

    @Test
    public void testGsonSerialize() throws Exception {
        Person person = new Person("Bill");
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(person);
        MockHttpServletRequestBuilder request = post("/some/path/gsonSerialize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"firstName\":\"Bill\"}"));
    }

    @Test
    public void testJsonFile() throws Exception {
        String json = getJson("/data.json");
        MockHttpServletRequestBuilder request = post("/jr/string-example")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"dog\":\"Sonny\",\"cat\":\"Vernon\"}"));
    }

    String getJson(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
