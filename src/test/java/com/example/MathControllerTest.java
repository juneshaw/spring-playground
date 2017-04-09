package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Math!"));
    }

    @Test
    public void testPi() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculateAdd() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testCalculateDefault() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void testSubtract() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testMultiply() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));

    }

    @Test
    public void testDivide() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=15&y=3");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("15 / 3 = 5"));
    }

    @Test
    public void testSum() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/sum?n=4&n=5&n=6");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolumeWithPost() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/3/4/5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testVolumeWithGet() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/3/4/5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testVolumeWithPut() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/math/volume/3/4/5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testVolumeWithPatch() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.patch("/math/volume/3/4/5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testAreaCircle() throws Exception {
        Circle circle = new Circle(4);
        Gson gson = new Gson();
        JsonObject jsonObject = (JsonObject) gson.toJsonTree(circle);
        jsonObject.addProperty("type", "circle");
        String jsonString = jsonObject.toString();
        System.out.println("jsonString: " + jsonString
        );
        RequestBuilder request = MockMvcRequestBuilders
                .post("/math/area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }
}
