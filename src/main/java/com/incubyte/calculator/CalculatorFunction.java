package com.incubyte.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorFunction.class);

    public int add(String numbers) {
        String delimiter = getDelimiter(numbers);
        String[] splitNumbers = splitString(numbers, delimiter);
        List<Integer> integerArray = convertStringArrayToIntegerArray(splitNumbers);
        return sum(integerArray);
    }

    private String getDelimiter(String numbers) {
        return null;
    }

    private String[] splitString(String numbers, String delimiter) {
        return null;
    }

    private List<Integer> convertStringArrayToIntegerArray(String[] numbers) {
        return null;
    }

    private int sum(List<Integer> numbers) {
        return 0;
    }
}
