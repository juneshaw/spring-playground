package com.ioc_and_config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMyConfig {
    @Autowired
    MyConfig myConfig;

    @Test
    public void testMyConfig() throws Exception {
        assertThat(myConfig.isCaseSensitive()).isEqualTo(false);
        assertThat(myConfig.getWords().getSkip().toArray()[0]).isEqualTo("the");
        assertThat(myConfig.getWords().getSkip().toArray()[1]).isEqualTo("an");
        assertThat(myConfig.getWords().getSkip().toArray()[2]).isEqualTo("a");
    }
}
