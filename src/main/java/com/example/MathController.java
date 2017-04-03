package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
