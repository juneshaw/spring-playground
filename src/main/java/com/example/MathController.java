package com.example;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

    @RequestMapping("/volume/{x}/{y}/{z}")
    public String getVolume(
            @PathVariable String x,
            @PathVariable String y,
            @PathVariable String z) throws Exception
    {
        Integer volume = MathService.volume(
                Integer.parseInt(x),
                Integer.parseInt(y),
                Integer.parseInt(z)
        );
        String dimensions =
                x + "x" + y + "x" + z;
        return MathService.formatFormula(
                "volume",
                dimensions,
                "rectangle",
                volume.toString());
    }

    @PostMapping(value = "/area")
    public String getArea(
        @RequestBody Map<String, String> params) {
        String type = params.get("type");
        String radius, x, y;
        Double result;
        String resultStr;
        ShapeImpl shape = null;
        switch (type) {
            case "circle" :
                radius = params.get("radius");
                shape = new Circle(Integer.parseInt(radius));
                break;
            case "rectangle" :
                x = params.get("x");
                y = params.get("y");
                shape = new Rectangle(
                        Double.parseDouble(x),
                        Double.parseDouble(y));
                break;
            default:
                break;
        }
        if (shape != null) {
            return shape.toString();
        } else {
            return("Error");
        }
    }

}
