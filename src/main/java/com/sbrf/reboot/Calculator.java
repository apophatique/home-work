package com.sbrf.reboot;

import com.sbrf.reboot.calculatorutils.InvalidBitwiseDirectionException;
import com.sbrf.reboot.calculatorutils.ShiftDirection;

/**
 * Класс, позволяющий выполнять некоторые арифметические операции.
 */
public class Calculator {
    /**
     * Операция сложения
     *
     * @param firstNum  - первое слагаемое
     * @param secondNum - второе слагаемое
     * @return сумма
     */
    public double getAddition(final double firstNum, final double secondNum) {
        return firstNum + secondNum;
    }

    /**
     * Операция вычитания
     *
     * @param minuend    - уменьшаемое
     * @param subtrahend - вычитаемое
     * @return разность
     */
    public double getSubtraction(final double minuend, final double subtrahend) {
        return minuend - subtrahend;
    }

    /**
     * Операция умножения
     *
     * @param multiplier   - первый множитель
     * @param multiplicand - второй множитель
     * @return - произведелние
     */
    public double getMultiplication(final double multiplier, final double multiplicand) {
        return multiplier * multiplicand;
    }

    /**
     * Операция деления
     *
     * @param dividend - делимое
     * @param divisor  - делитель
     * @return - частное
     */
    public double getDivision(final double dividend, final double divisor) {
        return dividend / divisor;
    }

    /**
     * Операция остатка от деления
     *
     * @param dividend - делимое
     * @param divisor  - делитель
     * @return остаток от деления
     */
    public int getRemainderOfDivision(final int dividend, final int divisor) {
        return dividend % divisor;
    }

    /**
     * Операция возведения в степень
     *
     * @param value    - число
     * @param powValue - степень
     * @return число value в степени powValue
     */
    public double getPow(final int value, final int powValue) {
        return Math.pow(value, powValue);
    }

    /**
     * Операция побитового сдвига.
     *
     * @param value          - число для сдвига.
     * @param shiftValue     - количество сдвигов.
     * @param shiftDirection - {@link ShiftDirection} направление сдвига
     * @return число value, сдвинутое shiftValue раз в сторону shiftDirection
     * @throws Exception если невалидное значение shiftDirection
     */
    public int getBitwiseShift(final int value, final int shiftValue, final ShiftDirection shiftDirection) throws Exception {
        if (shiftDirection == ShiftDirection.LEFT) {
            return value << shiftValue;
        }
        if (shiftDirection == ShiftDirection.RIGHT) {
            return value >> shiftValue;
        }
        throw new InvalidBitwiseDirectionException(String.format("Shift direction value is %s", shiftDirection));
    }
}
