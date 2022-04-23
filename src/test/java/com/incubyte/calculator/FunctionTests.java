package com.incubyte.calculator;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class FunctionTests {

    CalculatorFunction calculatorFunction;

    @Before
    public void setUp() {
       calculatorFunction  = new CalculatorFunction();
    }

    @Test
    public void whenAddIsCalledWithNumbersSeparatedByCommaThenAddAllNumbers() {
        assertEquals(10, calculatorFunction.add("1,2,3,4"));
    }

    @Test
    public void whenAddIsCalledWithNumbersSeparatedByDelimiterThenAddAllNumbers() {
       assertEquals(10, calculatorFunction.add("//;1;2;3;4"));
    }

    @Test
    public void whenAddIsCalledWithNumbersSeparatedByNewLineThenAddAllNumbers() {
        assertEquals(10, calculatorFunction.add("\n1\n2\n3\n4"));
    }

    @Test
    public void whenAddIsCalledWithNumbersSeparatedByCombinationOfDelimitersThenAddAllNumbers() {
        assertEquals(10, calculatorFunction.add("//;\n1,\n2\n;3\n;4"));
    }

    @Test
    public void whenAddIsCalledWithInvalidNumbersThenIgnoreThem() {
        assertEquals(10, calculatorFunction.add("//;\n1,2,InvalidNumber\n;3\n;4"));
    }

    @Test
    public void whenGetDelimiterIsCalledWithValidDelimiterThenReturnTheDelimiter() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getDelimiter = CalculatorFunction.class.getDeclaredMethod("getDelimiter", String.class);
        getDelimiter.setAccessible(true);
        String result = (String) getDelimiter.invoke(calculatorFunction,"//;");
        assertEquals(result, ";");
    }

    @Test
    public void whenGetDelimiterIsCalledWithNoDelimiterThenReturnEmptyString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getDelimiter = CalculatorFunction.class.getDeclaredMethod("getDelimiter", String.class);
        getDelimiter.setAccessible(true);
        String result = (String) getDelimiter.invoke(calculatorFunction,"1,2,3,4");
        assertEquals(result, "");
    }

    @Test
    public void whenGetDelimiterIsCalledWithDelimiterInitializerAndNoDelimiterThenThrowError() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getDelimiter = CalculatorFunction.class.getDeclaredMethod("getDelimiter", String.class);
        getDelimiter.setAccessible(true);
        String result = (String) getDelimiter.invoke(calculatorFunction,"//");
        assertEquals(result, "");
    }

    @Test
    public void whenSplitStringIsCalledWithStringSeparatedByCommaThenSplitTheString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method splitString = CalculatorFunction.class.getDeclaredMethod("splitString", String.class, String.class);
        splitString.setAccessible(true);
        String[] result = (String[]) splitString.invoke(calculatorFunction,"1,2,3,4", "");
        assertEquals(result[0], "1");
        assertEquals(result[1], "2");
        assertEquals(result[2], "3");
        assertEquals(result[3], "4");
    }

    @Test
    public void whenSplitStringIsCalledWithStringSeparatedByValidDelimitersThenSplitTheString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method splitString = CalculatorFunction.class.getDeclaredMethod("splitString", String.class, String.class);
        splitString.setAccessible(true);
        String[] result = (String[]) splitString.invoke(calculatorFunction,"1;2,3\n4", ";");
        assertEquals(result[0], "1");
        assertEquals(result[1], "2");
        assertEquals(result[2], "3");
        assertEquals(result[3], "4");
    }

    @Test
    public void whenConvertStringArrayToIntegerArrayIsCalledWithValidNumberThenConvert() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] stringArray = {"1", "2", "3"};

        Method convertStringArrayToIntegerArray = CalculatorFunction.class.getDeclaredMethod("convertStringArrayToIntegerArray", String[].class);
        convertStringArrayToIntegerArray.setAccessible(true);
        List<Integer> result = (List<Integer>) convertStringArrayToIntegerArray.invoke(calculatorFunction, new Object[]{stringArray});
        assertThat(result.get(0), is(equalTo(1)));
        assertThat(result.get(1), is(equalTo(2)));
        assertThat(result.get(2), is(equalTo(3)));
    }

    @Test
    public void whenConvertStringArrayToIntegerArrayIsCalledWithInvalidNumbersThenIgnore() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] stringArray = {"1", "InvalidNumber", "3"};

        Method convertStringArrayToIntegerArray = CalculatorFunction.class.getDeclaredMethod("convertStringArrayToIntegerArray", String[].class);
        convertStringArrayToIntegerArray.setAccessible(true);
        List<Integer> result = (List<Integer>) convertStringArrayToIntegerArray.invoke(calculatorFunction, new Object[]{stringArray});
        assertThat(result.get(0), is(equalTo(1)));
        assertThat(result.get(1), is(equalTo(3)));
    }

    @Test
    public void whenSumIsCalledWithArrayListOfNumbersThenReturnSum() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        Method sum = CalculatorFunction.class.getDeclaredMethod("sum", List.class);
        sum.setAccessible(true);
        int result = (int) sum.invoke(calculatorFunction, numbers);
        assertThat(result, is(equalTo(10)));
    }

}
