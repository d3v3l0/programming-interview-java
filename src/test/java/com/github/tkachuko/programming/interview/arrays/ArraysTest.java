package com.github.tkachuko.programming.interview.arrays;

import com.github.tkachuko.programming.interview.arrays.util.ThreeValues;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.tkachuko.programming.interview.arrays.Arrays.*;
import static org.junit.Assert.*;

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
                new Object[]{asList(1, 3, 452, 1, 1, 2, 3, 1, 2, 3), 0},
                new Object[]{asList(1, 1, 1, 1), 0},
                new Object[]{asList(1, 2, 1, 3), 0},
                new Object[]{asList(1, 2, 3, 1), 1}
        };
    }

    @Test
    @Parameters
    public void shouldSortThreeValueArrayInASequentialOrder(List<ThreeValues> array) {
        threeValueSequentialSort(array);

        int firstIndex = array.lastIndexOf(ThreeValues.FIRST);
        int secondIndex = array.lastIndexOf(ThreeValues.SECOND);
        int thirdIndex = array.lastIndexOf(ThreeValues.THIRD);
        boolean firstValueIsSequential = firstIndex == -1 || array.subList(0, firstIndex).stream().allMatch(i -> i == ThreeValues.FIRST);
        boolean secondValueIsSequential = secondIndex == -1 || array.subList(firstIndex + 1, secondIndex).stream().allMatch(i -> i == ThreeValues.SECOND);
        boolean thirdValueIsSequential = thirdIndex == -1 || array.subList(secondIndex + 1, thirdIndex).stream().allMatch(i -> i == ThreeValues.THIRD);
        assertTrue(firstValueIsSequential && secondValueIsSequential && thirdValueIsSequential);
    }

    public Object parametersForShouldSortThreeValueArrayInASequentialOrder() {
        return new Object[]{
                new Object[]{Arrays.asList(
                        ThreeValues.FIRST, ThreeValues.SECOND,
                        ThreeValues.FIRST, ThreeValues.SECOND,
                        ThreeValues.THIRD, ThreeValues.FIRST)
                },
                new Object[]{Arrays.asList(
                        ThreeValues.THIRD, ThreeValues.FIRST,
                        ThreeValues.FIRST, ThreeValues.THIRD,
                        ThreeValues.THIRD, ThreeValues.SECOND)
                },
                new Object[]{Arrays.asList(
                        ThreeValues.THIRD, ThreeValues.FIRST,
                        ThreeValues.FIRST, ThreeValues.THIRD,
                        ThreeValues.SECOND, ThreeValues.SECOND, ThreeValues.FIRST)
                }
        };
    }

    @Test
    @Parameters
    public void shouldSortBooleanValuesSequentially(List<Boolean> array, boolean headValue) {
        booleanValueSequentialSort(array, headValue);

        int headValuePosition = array.lastIndexOf(headValue);
        boolean headValueIsInTheBeginning = array.subList(0, headValuePosition).stream().allMatch(i -> i == headValue);
        boolean allOtherValuesAreNotAsHeadOne = array.subList(headValuePosition + 1, array.size()).stream().allMatch(i -> i == !headValue);
        assertTrue(headValueIsInTheBeginning && allOtherValuesAreNotAsHeadOne);
    }

    public Object parametersForShouldSortBooleanValuesSequentially() {
        return new Object[]{
                new Object[]{asList(true, true, false, false, true, false), false},
                new Object[]{asList(true, true, false, false, true, false), true},
                new Object[]{asList(false, true, false, false, true, false), true},
                new Object[]{asList(false, true, false, false, true, true), false}
        };
    }

    @Test
    @Parameters
    public void shouldAddOneToArrayAsNumber(List<Integer> number, List<Integer> incremented) {
        plusOne(number);
        assertEquals(incremented, number);
    }

    public Object parametersForShouldAddOneToArrayAsNumber() {
        return new Object[]{
                new Object[]{
                        new ArrayList<>(asList(1, 2, 3)),
                        new ArrayList<>(asList(1, 2, 4))
                },
                new Object[]{
                        new ArrayList<>(asList(1, 2, 9)),
                        new ArrayList<>(asList(1, 3, 0))
                },
                new Object[]{
                        new ArrayList<>(asList(9, 9)),
                        new ArrayList<>(asList(1, 0, 0))
                }
        };
    }

    @Test
    @Parameters
    public void shouldDefineIfEndCanBeReached(List<Integer> board, boolean canReachEnd) {
        assertEquals(canReachEnd, canReachEnd(board));
    }

    public Object parametersForShouldDefineIfEndCanBeReached() {
        return new Object[]{
                new Object[]{asList(1, 1, 1), true},
                new Object[]{asList(3, 3, 0, 0, 0, 0, 1), false},
                new Object[]{asList(3, 2, 1, 1, 1, 0), true},
                new Object[]{asList(3, 2, 1, 1, 0, 0), false}
        };
    }

    @Test
    @Parameters
    public void shouldFindMinNumberOfStepsRequiredToWin(List<Integer> board, int minSteps) {
        assertEquals(minSteps, minNumberOfStepsToReachEnd(board));
    }

    public Object parametersForShouldFindMinNumberOfStepsRequiredToWin() {
        return new Object[]{
                new Object[]{asList(1, 1, 0), 2},
                new Object[]{asList(3, 2, 1, 1, 1, 0), 3},
                new Object[]{asList(3, 2, 1, 3, 1, 1), 2},
                new Object[]{asList(9, 2, 1, 3, 1, 1), 1},
                new Object[]{asList(1, 2, 9, 1, 1, 1), 3}
        };
    }

    @Test
    @Parameters
    public void shouldDeleteAllOccurrencesOfElementInArray(List<Integer> array, int key) {
        deleteKeyIn(array, key);
        assertFalse(array.stream().filter(i -> i == key).findAny().isPresent());
    }

    public Object parametersForShouldDeleteAllOccurrencesOfElementInArray() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4, 5, 6), 1},
                new Object[]{asList(1, 1, 3, 4, 5, 6), 1},
                new Object[]{asList(1, 1, 3, 4, 1, 6), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 6), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 6, 1), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 1, 1), 1},
                new Object[]{asList(), 1},
                new Object[]{asList(5, 5), 6}
        };
    }

    @Test
    @Parameters
    public void shouldDeleteDuplicatesInSortedArray(List<Integer> array) {
        List<Integer> expected = new ArrayList<>(new HashSet<>(array));
        removeDuplicatesInSorted(array);
        assertEquals(expected, array.stream().filter(i -> i != 0).collect(Collectors.toList()));
    }

    public Object parametersForShouldDeleteDuplicatesInSortedArray() {
        return new Object[]{
                new Object[]{asList(1, 1, 1, 2, 3, 3, 3, 3, 4, 5, 5)},
                new Object[]{asList(1, 1, 1, 1)},
                new Object[]{asList(1, 2, 3, 4, 5)}
        };
    }

    @Test
    @Parameters
    public void shouldDefineMaximumProfitOfOneStockSale(List<Integer> prices, int maxMargin) {
        assertEquals(maxMargin, bestStockMargin(prices));
    }

    public Object parametersForShouldDefineMaximumProfitOfOneStockSale() {
        return new Object[]{
                new Object[]{asList(1, 1, 1, 12, 3, 4, 56, 6, 3, 55), 55},
                new Object[]{asList(110, 100, 90, 95, 89, 87, 80, 67, 100), 33},
                new Object[]{asList(110, 100, 89, 95, 89, 96, 90, 100), 11}
        };
    }

    @Test
    @Parameters
    public void shouldFindLengthOfLongestSubarrayWithEqualElements(List<Integer> array, int maxLength) {
        assertEquals(maxLength, lengthOfLongestSubarrayWithEqualElements(array));
    }

    public Object parametersForShouldFindLengthOfLongestSubarrayWithEqualElements() {
        return new Object[]{
                new Object[]{asList(1, 1, 1, 12, 3, 4, 56, 6, 3, 55), 3},
                new Object[]{asList(1, 1, 1, 2, 2, 1, 1, 1, 1), 4},
                new Object[]{asList(1, 1, 2, 2, 3, 3, 3, 3, 2, 2), 4}
        };
    }

    @Test
    @Parameters
    public void shouldFindLAllPrimesUpToNumber(int limit, Set<Integer> primes) {
        assertEquals(primes, allPrimesUpTo(limit));
    }

    public Object parametersForShouldFindLAllPrimesUpToNumber() {
        return new Object[]{
                new Object[]{5, asSet(1, 2, 3, 5)},
                new Object[]{11, asSet(1, 2, 3, 5, 7, 11)}
        };
    }

    @Test
    @Parameters
    public void shouldApplyPermutationToArray(List<Integer> array, List<Integer> permutation, List<Integer> permuted) {
        assertEquals(permuted, apply(array, permutation));
    }

    public Object parametersForShouldApplyPermutationToArray() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4), asList(2, 0, 1, 3), asList(2, 3, 1, 4)},
                new Object[]{asList(1, 2, 3, 4), asList(3, 2, 1, 0), asList(4, 3, 2, 1)},
                new Object[]{asList(1, 2, 3, 4), asList(0, 1, 2, 3), asList(1, 2, 3, 4)}
        };
    }

    @Test
    @Parameters
    public void shouldReturnInversePermutation(List<Integer> permutation, List<Integer> inversePerputation) {
        assertEquals(inversePerputation, inversePermutation(permutation));
    }

    public Object parametersForShouldReturnInversePermutation() {
        return new Object[]{
                new Object[]{asList(3, 0, 2, 1), asList(1, 3, 2, 0)},
                new Object[]{asList(3, 2, 1, 0), asList(3, 2, 1, 0)},
                new Object[]{asList(0, 1, 2, 3), asList(0, 1, 2, 3)}
        };
    }

    @Test
    @Parameters
    public void shouldReturnNextPermutationInOrder(List<Integer> permutation, List<Integer> nextPermutation) {
        nextPermutation(permutation);
        assertEquals(nextPermutation, permutation);
    }

    public Object parametersForShouldReturnNextPermutationInOrder() {
        return new Object[]{
                new Object[]{asList(0, 1, 2, 3), asList(0, 1, 3, 2)},
                new Object[]{asList(0, 1, 3, 2), asList(0, 2, 1, 3)},
                new Object[]{asList(0, 2, 1, 3), asList(0, 2, 3, 1)},
                new Object[]{asList(0, 2, 3, 1), asList(0, 3, 1, 2)},
                new Object[]{asList(0, 3, 1, 2), asList(0, 3, 2, 1)},
                new Object[]{asList(0, 3, 2, 1), asList(1, 0, 2, 3)},
                new Object[]{asList(6, 2, 1, 5, 4, 3, 0), asList(6, 2, 3, 0, 1, 4, 5)}
        };
    }

    @Test
    @Parameters
    public void shouldReturnPreviousPermutationInOrder(List<Integer> permutation, List<Integer> previousPermutation) {
        previousPermutation(permutation);
        assertEquals(previousPermutation, permutation);
    }

    public Object parametersForShouldReturnPreviousPermutationInOrder() {
        return new Object[]{
                new Object[]{asList(0, 1, 3, 2), asList(0, 1, 2, 3)},
                new Object[]{asList(0, 2, 1, 3), asList(0, 1, 3, 2)},
                new Object[]{asList(0, 2, 3, 1), asList(0, 2, 1, 3)},
                new Object[]{asList(0, 3, 1, 2), asList(0, 2, 3, 1)},
                new Object[]{asList(0, 3, 2, 1), asList(0, 3, 1, 2)},
                new Object[]{asList(1, 0, 2, 3), asList(0, 3, 2, 1)},
                new Object[]{asList(6, 2, 3, 0, 1, 4, 5), asList(6, 2, 1, 5, 4, 3, 0)}
        };
    }

    @Test
    @Parameters
    public void shouldReturnKthPermutationInOrder(int size, int k, List<Integer> kthPermutation) {
        assertEquals(kthPermutation, kthPermutation(k, size));
    }

    public Object parametersForShouldReturnKthPermutationInOrder() {
        return new Object[]{
                new Object[]{4, 1, asList(0, 1, 3, 2)},
                new Object[]{4, 4, asList(0, 3, 1, 2)}
        };
    }

    @Test
    @Parameters
    public void shouldReturnRandomSubset(List<Integer> set, int subsetSize) {
        List<Integer> subset = randomSubSet(set, subsetSize);
        assertEquals(subsetSize, subset.size());
        assertEquals(subsetSize, new HashSet<>(subset).size());
    }

    public Object parametersForShouldReturnRandomSubset() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4, 5), 3},
                new Object[]{asList(1, 2, 3, 4, 5), 2},
                new Object[]{asList(1, 2, 3, 4, 5), 5}
        };
    }

    @Test
    @Parameters
    public void shouldFindMaxSumOfSubArray(int[] elements, int maxSum) {
        assertEquals(maxSum, subArrayWithMaxSum(elements));
    }

    public Object parametersForShouldFindMaxSumOfSubArray() {
        return new Object[]{
                new Object[]{new int[]{1, 2, 3, 4, 5}, 15},
                new Object[]{new int[]{-2, -3, 4, -1, -2, 1, 5, -3}, 7},
                new Object[]{new int[]{-2, -3, 4, -1, -2, -1, 1, 5, -3}, 6}
        };
    }
}
