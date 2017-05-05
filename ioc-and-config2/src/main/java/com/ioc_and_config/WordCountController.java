package com.ioc_and_config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WordCountController {

    private WordCounter wordCounter;

    public WordCountController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/words/count")
    public String countWord(@RequestBody String wordList) throws Exception{
        Map<String, Integer> resultString = new HashMap<String, Integer>();
        resultString = wordCounter.count(wordList);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(resultString);
        return json;
    }
}
