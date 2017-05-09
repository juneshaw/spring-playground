package com.galvanize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfig {

    @Autowired
    Config config;

    @Test
    public void testMyConfig() throws Exception {
        assertThat(config.getUrl()).isEqualTo("http://www.omdbapi.com");
    }
}
