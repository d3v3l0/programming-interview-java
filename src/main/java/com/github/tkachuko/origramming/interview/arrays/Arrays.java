package com.github.tkachuko.origramming.interview.arrays;

import com.github.tkachuko.origramming.interview.arrays.util.ThreeValues;

import java.util.*;

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
        for (int i = array.size() - 1, larger = array.size() - 1; i >= 0; i--) {
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

    /**
     * Removes all duplicates from given sorted array. Leaves elements 0 at the end.
     *
     * @param array sorted array
     */
    public static void removeDuplicatesInSorted(List<Integer> array) {
        int writeIndex = 1;
        int duplicates = 0;
        for (int i = 1; i < array.size(); i++) {
            if (!array.get(writeIndex - 1).equals(array.get(i))) {
                array.set(writeIndex++, array.get(i));
            } else {
                duplicates++;
            }
        }
        for (int i = 0; i < duplicates; i++) {
            array.set(array.size() - i - 1, 0);
        }
    }

    /**
     * Defined best margin possible on buying and selling one stock in any given day in predictions.
     *
     * @param deals prices of stock at given days
     * @return best margin
     */
    public static int bestStockMargin(List<Integer> deals) {
        int lowestPrice = Integer.MAX_VALUE;
        int margin = 0;
        for (Integer price : deals) {
            lowestPrice = Math.min(lowestPrice, price);
            margin = Math.max(margin, price - lowestPrice);
        }
        return margin;
    }

    /**
     * Finds the lengths of longest subarray where elements are equal.
     *
     * @param array input
     * @return length of longest subarray
     */
    public static int lengthOfLongestSubarrayWithEqualElements(List<Integer> array) {
        int lengthOfLongestSubarraySoFar = 1, maxLengthOfLongestSubarray = 1;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals(array.get(i - 1))) {
                lengthOfLongestSubarraySoFar++;
            } else {
                maxLengthOfLongestSubarray = Math.max(lengthOfLongestSubarraySoFar, maxLengthOfLongestSubarray);
                lengthOfLongestSubarraySoFar = 1;
            }
        }
        return Math.max(lengthOfLongestSubarraySoFar, maxLengthOfLongestSubarray);
    }

    /**
     * Finds all the prime numbers from 1 to n
     *
     * @param n upper limit of primes search
     * @return all primes from 1 to n
     */
    public static Set<Integer> allPrimesUpTo(int n) {
        Set<Integer> result = new HashSet<>();
        result.add(1);

        List<Boolean> memo = new ArrayList<>(Collections.nCopies(n + 1, true));

        for (int i = 2; i <= n; i++) {
            if (memo.get(i)) {
                result.add(i);
                for (int j = i; j <= n; j += i) {
                    memo.set(j, false);
                }
            }
        }
        return result;
    }

    /**
     * Applies permutation to given array. Let permutation be [1,2,0] and the array[a,b,c]. Then after permutation
     * method should return [b,c,a].
     *
     * @param array       array of elements
     * @param permutation as array
     * @return permuted array
     */
    public static List<Integer> apply(List<Integer> array, List<Integer> permutation) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(array.size(), -1));
        for (int i = 0; i < permutation.size(); i++) {
            result.set(permutation.get(i), array.get(i));
        }
        return result;
    }

    /**
     * Provides inverse permutation based on permutation specified in array
     *
     * @param permutation permutation as array
     * @return
     */
    public static List<Integer> inversePermutation(List<Integer> permutation) {
        List<Integer> inversePermutation = new ArrayList<>(Collections.nCopies(permutation.size(), -1));
        for (int i = 0; i < permutation.size(); i++) {
            inversePermutation.set(permutation.get(i), i);
        }
        return inversePermutation;
    }

    /**
     * Returns next permutation in comparable order such as (2,0,3) < (2,1,3)
     *
     * @param permutation the one to compute next from
     */
    public static void nextPermutation(List<Integer> permutation) {
        // Find index i of last element in permutation so that holds p[i] > p[i - 1], i--
        int lastIncreasingBackwardsIndex = permutation.size() - 1;
        while (permutation.get(lastIncreasingBackwardsIndex) < permutation.get(lastIncreasingBackwardsIndex - 1)) {
            lastIncreasingBackwardsIndex--;
        }

        // we are going to swap element at this index with one of the elements in increasing tail
        int minimumChangeIndex = lastIncreasingBackwardsIndex - 1;

        // swap element at p[lastIncreasingBackwardsIndex - 1] with the first one larger than it in tail
        int firstGreaterInTailIndex = permutation.size() - 1;
        while (firstGreaterInTailIndex > 0) {
            if (permutation.get(firstGreaterInTailIndex) > permutation.get(minimumChangeIndex)) {
                Collections.swap(permutation, firstGreaterInTailIndex, minimumChangeIndex);
                break;
            }
            firstGreaterInTailIndex--;
        }

        // reverse the tail to sort it
        Collections.reverse(permutation.subList(lastIncreasingBackwardsIndex, permutation.size()));
    }

    /**
     * Returns previous permutation in comparable order such as (2,0,3) < (2,1,3)
     *
     * @param permutation current permutation
     */
    public static void previousPermutation(List<Integer> permutation) {
        // Find index i of last element in permutation so that holds p[i] > p[i - 1], i--
        int lastIncreasingBackwardsIndex = permutation.size() - 1;
        while (permutation.get(lastIncreasingBackwardsIndex) > permutation.get(lastIncreasingBackwardsIndex - 1)) {
            lastIncreasingBackwardsIndex--;
        }

        // we are going to swap element at this index with one of the elements in decreasing tail
        int minimumChangeIndex = lastIncreasingBackwardsIndex - 1;

        // reverse tail
        Collections.reverse(permutation.subList(lastIncreasingBackwardsIndex, permutation.size()));

        // swap element at p[firstLesserInTailIndex] with the first one less than it in tail
        int firstLesserInTailIndex = lastIncreasingBackwardsIndex;
        while (firstLesserInTailIndex != permutation.size()) {
            if (permutation.get(firstLesserInTailIndex) < permutation.get(minimumChangeIndex)) {
                Collections.swap(permutation, firstLesserInTailIndex, minimumChangeIndex);
                break;
            }
            firstLesserInTailIndex++;
        }
    }

    /**
     * Returns k-th permutation of given size in an order starting from first permutation.
     *
     * @param k    order of permutation to return
     * @param size permutation size
     * @return k-th permutation
     */
    public static List<Integer> kthPermutation(int k, int size) {
        List<Integer> initial = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            initial.add(i);
        }
        for (int i = 0; i < k; i++) {
            nextPermutation(initial);
        }
        return initial;
    }

    /**
     * Returns the random subset of given size
     *
     * @param set        original set
     * @param subsetSize size of subset
     * @return random subset of given size
     */
    public static List<Integer> randomSubSet(List<Integer> set, int subsetSize) {
        List<Integer> subSet = new ArrayList<>(Collections.nCopies(subsetSize, 0));
        Random random = new Random();
        for (int i = 0; i < subsetSize; i++) {
            Collections.swap(set, i, i + random.nextInt(set.size() - i));
        }
        for (int i = 0; i < subsetSize; i++) {
            subSet.set(i, set.get(i));
        }
        return subSet;
    }

    public static <T> List<T> asList(T... elements) {
        return java.util.Arrays.asList(elements);
    }

    public static <T> Set<T> asSet(T... elements) {
        return new HashSet<>(java.util.Arrays.asList(elements));
    }
}
