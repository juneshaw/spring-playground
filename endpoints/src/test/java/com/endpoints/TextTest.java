package com.endpoints;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TextController.class)
public class TextTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void camelize() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/camelize")
                .param("original","this_is_a_thing");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("thisIsAThing"));
    }

    @Test
    public void capitalCamelize() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/camelize")
                .param("original","this_is_a_thing")
                .param("initialCap","true");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ThisIsAThing"));
    }

    @Test
    public void redact() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/redact")
                .param("original", "A little of this and a little of that")
                .param("badWord", "little");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("A ****** of this and a ****** of that"));
    }

    @Test
    public void redactTwoBadWords() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/redact")
                .param("original", "A little of this and a little of that")
                .param("badWord", "this")
                .param("badWord", "little");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("A ****** of **** and a ****** of that"));
    }

    @Test
    public void encode() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/encode")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("message","a little of this and a little of that")
                .param("key","mcxrstunopqabyzdefghijklvw");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("m aohhas zt hnog myr m aohhas zt hnmh"));
    }

    @Test
    public void sed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/s/little/lot")
                .contentType(MediaType.TEXT_PLAIN)
                .content("a little of this and a little of that");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("a lot of this and a lot of that"));
    }
}