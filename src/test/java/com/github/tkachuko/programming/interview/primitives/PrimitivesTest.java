package com.github.tkachuko.programming.interview.primitives;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tkachuko.programming.interview.primitives.Primitives.*;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PrimitivesTest {

    @Test
    @Parameters
    public void parityShouldBeOne(long word) {
        assertEquals(1, parity(word));
    }

    public Object parametersForParityShouldBeOne() {
        return new Object[]{
                new Object[]{123654156234156243L},
                new Object[]{1},
                new Object[]{4},
                new Object[]{12},
                new Object[]{Long.MAX_VALUE - 1},
        };
    }

    @Test
    @Parameters
    public void parityShouldBeZero(long word) {
        assertEquals(0, parity(word));
    }

    public Object parametersForParityShouldBeZero() {
        return new Object[]{
                new Object[]{123654156234156242L},
                new Object[]{2},
                new Object[]{5},
                new Object[]{13},
                new Object[]{Long.MAX_VALUE},
        };
    }

    @Test
    @Parameters
    public void shouldSwapBits(long word, int i, int j, long expected) {
        assertEquals(expected, swapBits(word, i, j));
    }

    public Object parametersForShouldSwapBits() {
        return new Object[]{
                new Object[]{13, 0, 1, 14},
                new Object[]{13, 1, 2, 11},
                new Object[]{13, 2, 3, 13}
        };
    }

    @Test
    @Parameters
    public void shouldReverseBitsInWord(long word, long reversed) {
        assertEquals(reversed, reverseBits(word));
    }

    public Object parametersForShouldReverseBitsInWord() {
        return new Object[]{
                new Object[]{2, 1},
                new Object[]{5, 5},
                new Object[]{13, 11}
        };
    }

    @Test
    @Parameters
    public void shouldFindClosestWithTheSameWeight(int input, int expected) {
        assertEquals(expected, closestWithTheSameWeight(input));
    }

    public Object parametersForShouldFindClosestWithTheSameWeight() {
        return new Object[]{
                new Object[]{6, 5},
                new Object[]{2, 1},
                new Object[]{13, 14}
        };
    }

    @Test
    @Parameters
    public void shouldReverseDigitsInNumber(int input, int reversed) {
        assertEquals(reversed, reverseDigits(input));
    }

    public Object parametersForShouldReverseDigitsInNumber() {
        return new Object[]{
                new Object[]{1, 1},
                new Object[]{10, 1},
                new Object[]{25, 52},
                new Object[]{-51, -15},
                new Object[]{-42, -24}
        };
    }

    @Test
    @Parameters
    public void shouldDetermineIfNumberIsBinaryPalindrome(long number, boolean isBinaryPolindrome) {
        assertEquals(isBinaryPolindrome, isBinaryPalindrome(number));
    }

    public Object parametersForShouldDetermineIfNumberIsBinaryPalindrome() {
        return new Object[]{
                new Object[]{21, true},
                new Object[]{2, false},
                new Object[]{21845, true},
                new Object[]{-51, false},
                new Object[]{31, true}
        };
    }

    @Test
    @Parameters
    public void shouldDetermineIfNumberIsDecimalPalindrome(long number, boolean isDecimalPalindrome) {
        assertEquals(isDecimalPalindrome, isDecimalPalindrome(number));
    }

    public Object parametersForShouldDetermineIfNumberIsDecimalPalindrome() {
        return new Object[]{
                new Object[]{1, true},
                new Object[]{10, false},
                new Object[]{252, true},
                new Object[]{-51, false},
                new Object[]{1111, true}
        };
    }

    @Test
    @Parameters
    public void shouldDetermineIfNumberIfPrime(int number, boolean isPrime) {
        assertEquals(isPrime, isPrime(number));
    }

    public Object parametersForShouldDetermineIfNumberIfPrime() {
        return new Object[]{
                new Object[]{13, true},
                new Object[]{26, false},
                new Object[]{2, true},
                new Object[]{121, false},
                new Object[]{1, true}
        };
    }
}
