package com.sbrf.reboot.calculator;

import com.sbrf.reboot.Calculator;
import com.sbrf.reboot.calculatorutils.InvalidBitwiseDirectionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    @Test
    void getAddition() {
        assertEquals(9, new Calculator().getAddition(4, 5));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, new Calculator().getSubtraction(4, 5));
    }

    @Test
    void getMultiplication() {
        assertEquals(20, new Calculator().getMultiplication(4, 5));
    }

    @Test
    void getDivision() {
        assertEquals(3, new Calculator().getDivision(9, 3));
    }

    @Test
    void getRemainderOfDivisionShouldPassSuccess() {
        assertEquals(1, new Calculator().getRemainderOfDivision(5, 2));
    }

    @Test
    void getPowShouldPassSuccess() {
        assertEquals(27, new Calculator().getPow(3, 3));
    }

    @Test
    void getBitwiseShiftShouldThrowException() throws Exception {
        assertThrows(
                InvalidBitwiseDirectionException.class,
                () -> new Calculator().getBitwiseShift(
                        2,
                        4,
                        null
                )
        );
    }

    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}