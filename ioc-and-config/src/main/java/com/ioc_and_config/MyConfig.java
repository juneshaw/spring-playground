package com.ioc_and_config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("wordCount")
public class MyConfig {
    private boolean caseSensitive;

    private Words words;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public static class Words {
        List<String> skip;

        public List<String> getSkip() {
            return skip;
        }

        public void setSkip(List<String> skip) {
            this.skip = skip;
        }
    }
}
