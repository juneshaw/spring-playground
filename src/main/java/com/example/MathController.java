package com.example;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("")
    public String getWelcome() {
        return "Welcome to Math!";
    }

    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    String formatExpression(
            Collection operands,
            String operator,
            String result) {

        String expression;
        StringBuilder stringBuilder = new StringBuilder();
        Iterator operandIterator = operands.iterator();
        stringBuilder.append(operandIterator.next());
        while (operandIterator.hasNext()) {
            stringBuilder.append(" " + operator + " " + operandIterator.next());
        }
        stringBuilder.append(" = " + result);
        return stringBuilder.toString();
    }

    @PostMapping("/sum")
    public String sum(
            @RequestParam MultiValueMap<String, String> queryString)
    {
        List<String> queryParams = new ArrayList<String>();
        List<Integer> queryNums = new ArrayList<Integer>();
        Integer sumParams;
        String resultStr;

        queryParams = queryString.get("n");
        for(String s : queryParams) queryNums.add(Integer.valueOf(s));
        sumParams = MathService.sum(queryNums);
        return formatExpression(
                queryParams,
                "+",
                sumParams.toString());
    }

    @GetMapping("/calculate")
    public String getCalculate(
            @RequestParam(required=false, defaultValue="add") String operation,
            @RequestParam String x,
            @RequestParam String y)
    {

        Integer resultInt, xInt, yInt;
        String operand, resultStr;
        xInt = Integer.parseInt(x);
        yInt = Integer.parseInt(y);

        switch (operation) {
            case "add":
                operand = "+";
                resultInt = xInt + yInt;
                break;
            case "subtract":
                operand = "-";
                resultInt = xInt - yInt;
                break;
            case "multiply":
                operand = "*";
                resultInt = xInt * yInt;
                break;
            case "divide":
                operand = "/";
                resultInt = xInt / yInt;
                break;
            default:
                operand = "_";
                resultInt = xInt + yInt;
                break;
        }
        resultStr = String.format(
                "%s %s %s = %s",
                x,
                operand,
                y,
                resultInt.toString()
        );
        return resultStr;
    }

}
