package com.github.tkachuko.origramming.interview.arrays;

import com.github.tkachuko.origramming.interview.arrays.util.ThreeValues;

import java.util.Collections;
import java.util.List;

public class Arrays {

    public static void dutchFlagSort(List<Integer> array, int pivotIndex) {
        int pivot = array.get(pivotIndex);
        for (int i = 0, smaller = 0; i < array.size(); i++) {
            if (array.get(i) < pivot) Collections.swap(array, i, smaller++);
        }
        for (int i = array.size() - 1, larger = array.size() - 1; i >= 0 && array.get(i) >= pivot; i--) {
            if (array.get(i) > pivot) Collections.swap(array, i, larger--);
        }
    }

    public static void threeValueSequentialSort(List<ThreeValues> array) {
        for (int i = 0, first = 0; i < array.size(); i++) {
            if (array.get(i) == ThreeValues.FIRST) Collections.swap(array, i, first++);
        }
        for (int i = array.size() - 1, third = array.size() - 1; i >= 0 && array.get(i) != ThreeValues.FIRST; i--) {
            if (array.get(i) == ThreeValues.THIRD) Collections.swap(array, i, third--);
        }
    }

    public static void booleanValueSequentialSort(List<Boolean> array, boolean headValue) {
        for (int i = 0, headValuePosition = 0; i < array.size(); i++) {
            if (array.get(i) == headValue) Collections.swap(array, i, headValuePosition++);
        }
    }

    public static void plusOne(List<Integer> number) {
        int over = 1;
        for (int i = number.size() - 1; i >= 0; i--) {
            int digit = number.get(i) + over;
            over = digit / 10;
            number.set(i, digit % 10);
        }
        if (over >= 10) {
            number.add(0, over % 10);
            number.add(0, over / 10);
        } else if (over != 0) {
            number.add(0, over);
        }
    }

    public static <T> List<T> asList(T... elements) {
        return java.util.Arrays.asList(elements);
    }
}
