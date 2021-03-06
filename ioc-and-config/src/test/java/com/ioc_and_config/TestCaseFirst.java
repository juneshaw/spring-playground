package com.ioc_and_config;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestCaseFirst {

    private String testString;

    private Map<String, Integer> testWordCount;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public Map<String, Integer> getTestWordCount() {
        return testWordCount;
    }

    public void setTestWordCount(Map<String, Integer> testWordCount) {
        this.testWordCount = testWordCount;
    }

    public TestCaseFirst create() {
        this.setTestString("A brown cow jumps over a brown fox");
        this.setTestWordCount(new HashMap<>());
        this.testWordCount.put("A", 1);
        this.testWordCount.put("brown", 2);
        this.testWordCount.put("cow", 1);
        this.testWordCount.put("jumps", 1);
        this.testWordCount.put("over", 1);
        this.testWordCount.put("a", 1);
        this.testWordCount.put("fox", 1);
        return this;
    }
}
