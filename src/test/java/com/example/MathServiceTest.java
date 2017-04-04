package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MathController.class)
public class MathServiceTest {

    @Test
    public void testSum() throws Exception {
        List<Integer> operands = Arrays.asList(2, 3, 4, 5);
        assertEquals(Integer.valueOf(14), MathService.sum(operands));
    }

    @Test
    public void testFormatExpression() throws Exception {
        List<String> operands = Arrays.asList("2", "3", "4", "5");
        assertEquals(
                "2 + 3 + 4 + 5 = 14",
                MathService.formatExpression(
                        "+",
                        operands,
                        "14"));

    }
}
