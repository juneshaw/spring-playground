package com.example;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    @PostMapping("/sum")
    public String sum(
            @RequestParam MultiValueMap<String, String> queryString)
    {
        List<String> queryParams = new ArrayList<String>();
        List<Integer> queryNums = new ArrayList<Integer>();
        Integer resultNum;

        queryParams = queryString.get("n");
        for(String s : queryParams) queryNums.add(Integer.valueOf(s));
        resultNum = MathService.sum(queryNums);
        return MathService.formatExpression(
                "add",
                queryParams,
                resultNum.toString());
    }

    @GetMapping("/calculate")
    public String getCalculate(
            @RequestParam(required=false, defaultValue="add") String operation,
            @RequestParam String x,
            @RequestParam String y) throws Exception
    {

        Integer resultNum, xNum, yNum;
        xNum = Integer.parseInt(x);
        yNum = Integer.parseInt(y);
        List<String> queryParams = Arrays.asList(x, y);

        try {
            resultNum = MathService.calculate(operation, xNum, yNum);
            return MathService.formatExpression(
                    operation,
                    queryParams,
                    resultNum.toString());
        }
        catch(Exception exc) {
            exc.printStackTrace();
            return exc.toString();
        }
    }

    @RequestMapping("/volume")
    public String getVolume(
            @RequestParam String x,
            @RequestParam String y,
            @RequestParam String z) throws Exception
    {
        return MathService.volume(
                Integer.parseInt(x),
                Integer.parseInt(y),
                Integer.parseInt(z)
        );

    }


}
