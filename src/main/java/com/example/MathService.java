package com.example;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathService {
    private static Map<String, String> operators =
        new HashMap<String, String>() {{
            put("add", "+");
            put("subtract", "-");
            put("multiply", "*");
            put("divide", "/");
        }};

    public static Integer sum(Collection<Integer> operands)
    {
        int sum = 0;
        for (Integer operand : operands) sum += operand;
        return sum;
    }

    public static Integer calculate (
            String operator,
            Integer x,
            Integer y) throws Exception
    {
        Integer result;

        switch (operator) {
            case "add":
                result = x + y;
                break;
            case "subtract":
                result = x - y;
                break;
            case "multiply":
                result = x * y;
                break;
            case "divide":
                try {
                    result = x/y;
                }
                catch(Exception exc) {
                    result = 0;
                    exc.printStackTrace();
                }
                break;
            default:
                result = x + y;
                break;
        }
        return result;
    }

    public static String formatExpression(
            String operator,
            List<String> operands,
            String result)
    {
        String expression;
        String operatorSymbol = operators.get(operator);
        StringBuilder stringBuilder = new StringBuilder();
        Iterator operandIterator = operands.iterator();
        stringBuilder.append(operandIterator.next());
        while (operandIterator.hasNext()) {
            stringBuilder.append(" " + operatorSymbol + " " + operandIterator.next());
        }
        stringBuilder.append(" = " + result);
        return stringBuilder.toString();
    }
}
