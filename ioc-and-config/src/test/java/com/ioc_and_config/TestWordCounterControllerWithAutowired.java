package com.ioc_and_config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestWordCounterControllerWithAutowired {

    @Autowired
    TestCase testCase;

    @Autowired
    MockMvc mvc;

    @Autowired
    WordCounter wordCounter;

    @Test
    public void testWordCounterControllerWithAutowired() throws Exception {
        String testString;
        Map<String, Integer> expectedWordCount = new HashMap<>();
        testString = testCase.create().getTestString();
        expectedWordCount = wordCounter.count(testString);
        Gson gson = new GsonBuilder().create();
        String expectedWordCountString = gson.toJson(expectedWordCount);

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .content(testString);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedWordCountString));
    }
}