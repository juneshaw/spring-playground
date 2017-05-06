package com.ioc_and_config;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordCounter {

    private final MyConfig myConfig;

    public WordCounter(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    public Map<String, Integer> count(String wordList) {
        Integer wordCount;
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        List<String> words =
                myConfig.isCaseSensitive() ?
                Arrays.asList(wordList.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"))
                        :
                Arrays.asList(wordList.replaceAll("[^a-zA-Z ]", "").split("\\s+"));


        List<String> lowerCaseAcceptableWords =
                words
                .stream()
                .filter(word -> myConfig.getWords().getSkip().indexOf(word.toLowerCase()) < 0)
                .collect(Collectors.toList());

        for (String word : lowerCaseAcceptableWords) {
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

