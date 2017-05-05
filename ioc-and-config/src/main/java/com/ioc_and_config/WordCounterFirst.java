package com.ioc_and_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class WordCounterFirst {

    private MyConfig myConfig;

    @Bean
    public WordCounterFirst getWordCounter() {
        return new WordCounterFirst();
    }

    public Map<String, Integer> count(String wordList) {
        Integer wordCount;
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        Iterator mapIterator = wordCountMap.entrySet().iterator();
        List<String> words = Arrays.asList(wordList.split("\\s+"));
        for (String word : words) {
            wordCount = wordCountMap.get(word);
            if (wordCount != null){
                wordCountMap.put(word, wordCount+1);
            } else {
                wordCountMap.put(word, 1);
            }
        }
        return wordCountMap;
    }
}

