package com.github.tkachuko.origramming.interview.arrays;

import com.github.tkachuko.origramming.interview.arrays.util.ThreeValues;

import java.util.Collections;
import java.util.List;

public class Arrays {

    /**
     * Sorts the array in a way that all integers less than array[pivotIndex] (pivot) will be located on the left side
     * of the array, equal - in the middle, greater - on the right.
     *
     * @param array      input array
     * @param pivotIndex index of the element which will be used for comparison
     */
    public static void dutchFlagSort(List<Integer> array, int pivotIndex) {
        int pivot = array.get(pivotIndex);
        for (int i = 0, smaller = 0; i < array.size(); i++) {
            if (array.get(i) < pivot) Collections.swap(array, i, smaller++);
        }
        for (int i = array.size() - 1, larger = array.size() - 1; i >= 0 && array.get(i) >= pivot; i--) {
            if (array.get(i) > pivot) Collections.swap(array, i, larger--);
        }
    }

    /**
     * Modification of dutch sort for case when elements in array can take only 3 possible values
     *
     * @param array input array
     */
    public static void threeValueSequentialSort(List<ThreeValues> array) {
        for (int i = 0, first = 0; i < array.size(); i++) {
            if (array.get(i) == ThreeValues.FIRST) Collections.swap(array, i, first++);
        }
        for (int i = array.size() - 1, third = array.size() - 1; i >= 0 && array.get(i) != ThreeValues.FIRST; i--) {
            if (array.get(i) == ThreeValues.THIRD) Collections.swap(array, i, third--);
        }
    }

    /**
     * Modification of dutch sort for case when elements in array can take only 2 values
     *
     * @param array     input array
     * @param headValue value that should be contained in the beginning
     */
    public static void booleanValueSequentialSort(List<Boolean> array, boolean headValue) {
        for (int i = 0, headValuePosition = 0; i < array.size(); i++) {
            if (array.get(i) == headValue) Collections.swap(array, i, headValuePosition++);
        }
    }

    /**
     * Adds one to the number represented as array of digits
     *
     * @param number digits of the number
     */
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

    /**
     * Indicated if win can be achieved in a board game where each number on the board indicates how you can go from
     * that point. Win can be achieved if you can get to the end of the board.
     *
     * @param board board representation
     * @return if win can be achieved
     */
    public static boolean canReachEnd(List<Integer> board) {
        int lastIndex = board.size() - 1;
        int furthermostSoFar = 0;
        for (int i = 0; i <= furthermostSoFar && furthermostSoFar < lastIndex; i++) {
            furthermostSoFar = Math.max(furthermostSoFar, board.get(i) + i);
        }
        return furthermostSoFar >= lastIndex;
    }

    /**
     * Modification of canReachEnd to return minimum number of steps required to reach the end of board.
     *
     * @param board board representation
     * @return minimum number of steps required to win
     */
    public static int minNumberOfStepsToReachEnd(List<Integer> board) {
        if (!canReachEnd(board)) return -1;
        int lastIndex = board.size() - 1;
        int furthermostSoFar = 0;
        int steps = 0;
        for (int i = 0; i <= furthermostSoFar && furthermostSoFar < lastIndex; i++) {
            int candidate = board.get(i) + i;
            if (candidate > furthermostSoFar) {
                steps++;
                furthermostSoFar = candidate;
            }
        }
        return steps;
    }

    /**
     * Deletes all occurrences of given key in array but leaves number of 0 values at the end of array
     *
     * @param array to delete key from
     * @param key   value to delete
     */
    public static void deleteKeyIn(List<Integer> array, int key) {
        int writeIndex = 0;
        int removed = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != key) {
                array.set(writeIndex++, array.get(i));
            } else {
                removed++;
            }
        }
        for (int i = 0; i < removed; i++) {
            array.set(array.size() - i - 1, 0);
        }
    }

    public static <T> List<T> asList(T... elements) {
        return java.util.Arrays.asList(elements);
    }
}
