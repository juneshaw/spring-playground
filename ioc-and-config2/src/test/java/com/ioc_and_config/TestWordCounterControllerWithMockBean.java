package com.ioc_and_config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(WordCountController.class)
public class TestWordCounterControllerWithMockBean {

    TestCaseTwo testCase;

    @Autowired
    MockMvc mvc;

    @MockBean
    public WordCounter wordCounter;

    @Before
    public void initialize() {
        testCase = new TestCaseTwo().create();
        String testString = testCase.getTestString();
        Map<String, Integer> testWordCount = testCase.getTestWordCount();
        Mockito.when(wordCounter.count(testString)).thenReturn(testWordCount);
    }

    @Test
    public void testWordCounterControllerWithMockBean() throws Exception {
        Gson gson = new GsonBuilder().create();
        String expectedWordCount = gson.toJson(testCase.getTestWordCount());

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .content(testCase.getTestString());

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedWordCount));
    }
}