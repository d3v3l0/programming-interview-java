package com.github.tkachuko.origramming.interview.arrays;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tkachuko.origramming.interview.arrays.Arrays.dutchFlagSort;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ArraysTest {

    @Test
    @Parameters
    public void shouldSortArrayInDutchFlagManner(List<Integer> array, int pivotIndex) {
        int pivot = array.get(pivotIndex);

        dutchFlagSort(array, pivotIndex);

        int firstPivotIndex = array.indexOf(pivot);
        int lastPivotIndex = array.lastIndexOf(pivot);
        boolean allLessAreOnTheLeft = array.subList(0, firstPivotIndex).stream().allMatch(i -> i < pivot);
        boolean allPivotsAreTogether = array.subList(firstPivotIndex, lastPivotIndex).stream().allMatch(i -> i == pivot);
        boolean allGreaterAreOnTheRight = array.subList(lastPivotIndex + 1, array.size()).stream().allMatch(i -> i > pivot);
        assertTrue(allLessAreOnTheLeft && allPivotsAreTogether && allGreaterAreOnTheRight);
    }

    public Object parametersForShouldSortArrayInDutchFlagManner() {
        return new Object[]{
                new Object[]{Arrays.asList(1, 3, 452, 1, 1, 2, 3, 1, 2, 3), 0},
                new Object[]{Arrays.asList(1, 1, 1, 1), 0},
                new Object[]{Arrays.asList(1, 2, 1, 3), 0},
                new Object[]{Arrays.asList(1, 2, 3, 1), 1}
        };
    }
}
