package com.incubyte.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorFunction.class);

    public int add(String numbers) {
        String delimiter = getDelimiter(numbers);
        if(!delimiter.equals("")) numbers = numbers.substring(3);
        String[] splitNumbers = splitString(numbers, delimiter);
        List<Integer> integerArray = convertStringArrayToIntegerArray(splitNumbers);
        return sum(integerArray);
    }

    private String getDelimiter(String numbers) {
        if(numbers.contains("//")) {
            try {
                return Character.toString(numbers.charAt(2));
            } catch (IndexOutOfBoundsException exception) {
                LOGGER.error("There is no delimiter: ", exception.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    private String[] splitString(String numbers, String delimiter) {
        String regex = "[" + delimiter + ",\n]";
        return numbers.split(regex);
    }

    private List<Integer> convertStringArrayToIntegerArray(String[] numbers) {
        List<Integer> numberArray = new ArrayList<>();
        for (String number : numbers) {
            try {
                numberArray.add(Integer.parseInt(number));
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse {} into integer: {}", number, exception.getMessage());
            }
        }
        return numberArray;
    }

    private int sum(List<Integer> numbers) {
        Integer sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum.intValue();
    }
}
