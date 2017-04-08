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
    public void testCalculateAdd() throws Exception {
        assertEquals(Integer.valueOf(6),
                MathService.calculate("add", 2,4));
    }

    @Test
    public void testCalculateSubtract() throws Exception {
        assertEquals(Integer.valueOf(4),
                MathService.calculate("subtract", 6, 2));
    }


    @Test
    public void testCalculateMultiply() throws Exception {
        assertEquals(Integer.valueOf(100),
                MathService.calculate("multiply", 10, 10));
    }

    @Test
    public void testCalculateDivide() throws Exception {
        assertEquals(Integer.valueOf(3),
                MathService.calculate("divide", 9,3));
    }

    @Test
    public void testCalculateDivideIllegal() throws Exception {
        assertEquals(Integer.valueOf(0),
                MathService.calculate("divide", 10, 0));
    }

    @Test
    public void testFormatExpression() {
        List<String> operands = Arrays.asList("2", "3", "4", "5");
        assertEquals(
                "2 + 3 + 4 + 5 = 14",
                MathService.formatExpression(
                        "add",
                        operands,
                        "14"));

    }

    @Test
    public void testAreaCircle() {
        Circle circle = new Circle(2);
        assertEquals(
                MathService.formatArea(circle.type)
        )

    }
}
