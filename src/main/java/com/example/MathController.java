package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
