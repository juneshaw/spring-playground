package com.endpoints;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(TextService.class)
public class TextServiceTest {

    @Test
    public void testText() throws Exception {
        Assert.assertEquals(
                "thisIsAThing",
                TextService.camelize("this_is_a_thing")
        );
    }

    @Test
    public void testTextCapitalCamel() throws Exception {
        Assert.assertEquals(
                "ThisIsAThing",
                TextService.camelize("this_is_a_thing", true)
        );
    }

    @Test
    public void testTextNoCapitalCamel() throws Exception {
        Assert.assertEquals(
                "thisIsAThing",
                TextService.camelize("this_is_a_thing", false)
        );
    }

    @Test
    public void testRedact() throws Exception {
        Assert.assertEquals(
                "A ****** of this and a ****** of that",
                TextService.redact("A little of this and a little of that", "little")
        );
    }

    @Test
    public void testEncode() throws Exception {
        Assert.assertEquals("m aohhas zt hnog myr m aohhas zt hnmh",
                TextService.encodeMessage("a little of this and a little of that",
                        "mcxrstunopqabyzdefghijklvw")
        );
    }

    @Test
    public void testReplace() throws Exception {
        Assert.assertEquals(
                "a lot of this and a lot of that",
                TextService.replaceAll(
                        "a little of this and a little of that",
                        "little",
                        "lot")
        );
    }
}
