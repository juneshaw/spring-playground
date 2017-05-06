package com.ioc_and_config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestWordCounterUnit {

    @Autowired
    WordCounter wordCounter;

    @Autowired
    TestCase testCase;

    @Test
    public void testWordCounterUnit() throws Exception{
        String testString;
        Map<String, Integer> expectedWordCount = new HashMap<>();
        testString = testCase.create().getTestString();
        expectedWordCount = testCase.create().getTestWordCount();
        Map<String, Integer> actualWordCount = wordCounter.count(testString);
        assertEquals(actualWordCount, expectedWordCount);
    }
}
