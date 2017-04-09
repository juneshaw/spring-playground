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
        String radius, width, height;
        ShapeImpl shape;
        String error = null;
        switch (type) {
            case "circle" :
                radius = params.get("radius");
                try {
                    shape = new Circle(Integer.parseInt(radius));
                } catch (NumberFormatException e) {
                    error = "Invalid";
                }
                break;
            case "rectangle" :
                width = params.get("width");
                height = params.get("height");
                try {
                    shape = new Rectangle(
                            Integer.parseInt(width),
                            Integer.parseInt(height));
                } catch (NumberFormatException e) {
                    error = "Invalid";
                }
                break;
            default:
                break;
        }
        if (error != null) {
            return error;
        } else {
            return shape.toString();
        }
    }

}
