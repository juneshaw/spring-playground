package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math");
        this.mockMvc.perform(request)
                .andExpect(content().string("Welcome to Math!"));
    }

    @Test
    public void testPi() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
        this.mockMvc.perform(request)
                .andExpect(content().string("3.141592653589793"));
    }
}