package com.ioc_and_config;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.test.context.TestPropertySource;

        import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "myConfig.isCaseSensitive=false",
        "myConfig.words.skip[0]=the",
        "myConfig.words.skip[0]=an",
        "myConfig.words.skip[0]=a"
})


public class TestMyConfigPropertySource
{
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