package com.github.tkachuko.origramming.interview.arrays;

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

    public static <T> List<T> asList(T... elements) {
        return java.util.Arrays.asList(elements);
    }
}
