package com.example;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MathService {
    public static Integer sum(Collection<Integer> operands) {
        int sum = 0;
        for (Integer operand : operands) sum += operand;
        return sum;
    }
}
