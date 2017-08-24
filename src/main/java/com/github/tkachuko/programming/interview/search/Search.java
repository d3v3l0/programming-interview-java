package com.github.tkachuko.programming.interview.search;

import com.github.tkachuko.programming.interview.search.trie.common.alphabet.AlphabetEncoding;
import com.github.tkachuko.programming.interview.search.trie.memory.Trie;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Search {

    /**
     * Finds index of first element occurrence in a sorted list
     *
     * @param list    sorted list
     * @param element to find
     * @return index of first element occurrence or -1
     */
    public static int indexOfInSorted(List<Integer> list, int element) {
        int start = 0, end = list.size() - 1, result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int current = list.get(mid);
            if (current > element) end = mid - 1;
            else if (current < element) start = mid + 1;
            else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    /**
     * Finds index of last element occurrence in a sorted list
     *
     * @param list    sorted list
     * @param element to find
     * @return index of last element occurrence or -1
     */
    public static int lastIndexOfInSorted(List<Integer> list, int element) {
        int start = 0, end = list.size() - 1, result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int current = list.get(mid);
            if (current > element) end = mid - 1;
            else if (current < element) start = mid + 1;
            else {
                result = mid;
                start = mid + 1;
            }
        }
        return result;
    }

    /**
     * Finds local minima in list ascending from left to right and descending from right to left
     *
     * @param list of integers
     * @return local minima
     */
    public static int localMinima(List<Integer> list) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == 0 || mid == list.size() - 1) return -1;

            int minima = list.get(mid);
            int left = list.get(mid - 1);
            int right = list.get(mid + 1);

            if (minima <= left && minima <= right) return minima;
            else if (minima < right) end = mid;
            else if (minima > left) start = mid;
        }
        return -1;
    }

    /**
     * Searches for the first element that is greater than pivot in sorted array
     *
     * @param list  sorted array
     * @param pivot element to search greater
     * @return first element that is greater than pivot or pivot if not found
     */
    public static int firstGreaterInSorted(List<Integer> list, int pivot) {
        int start = 0, end = list.size() - 1, result = pivot;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int current = list.get(mid);
            if (current <= pivot) start = mid + 1;
            else {
                result = current;
                end = mid - 1;
            }
        }
        return result;
    }

    /**
     * Returns two integers where first is index of first pivot occurrence and second - its last
     *
     * @param list    sorted array
     * @param element occurrences to look for
     * @return first and last index of pivot occurrence or [-1, -1]
     */
    public static int[] occurrenceIntervalInSorted(List<Integer> list, int element) {
        return new int[]{indexOfInSorted(list, element), lastIndexOfInSorted(list, element)};
    }

    /**
     * Finds first element such that is equal to its index in sorted array
     *
     * @param list sorted array
     * @return element that is equal to its index
     */
    public static int elementAsIndexInSorted(List<Integer> list) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int index = start + (end - start) / 2;
            int element = list.get(index);
            int indexDifference = element - index;

            if (indexDifference == 0) return element;
            else if (indexDifference > 0) end = index - 1;
            else if (indexDifference < 0) start = index + 1;
            else start = index + 1;
        }
        return -1;
    }

    /**
     * Finds minimum in sorted unique shifted list
     *
     * @param list sorted unique shifted list
     * @return minimum
     */
    public static int minimumInSortedShifted(List<Integer> list) {
        int start = 0, end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < list.get(end)) end = mid;
            else start = mid + 1;
        }
        return list.get(start);
    }

    /**
     * Finds index of element in sorted unique shifted list
     *
     * @param list    sorted unique shifted list
     * @param element to find
     * @return index of element or -1
     */
    public static int indexOfElementInSortedShifted(List<Integer> list, int element) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (element == list.get(mid)) return mid;

            if (list.get(left) <= list.get(mid)) {
                if (list.get(left) <= element && element < list.get(mid)) right = mid - 1;
                else left = mid + 1;
            } else {
                if (list.get(mid) < element && element <= list.get(right)) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Tests if there are i, j such that list[i] + list[j] = given sum
     *
     * @param list   sorted list
     * @param target sum to be found
     * @return if there is given sum in list
     */
    public static boolean hasSum(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == target) return true;
            else if (sum < target) left++;
            else right--;
        }

        return false;
    }

    /**
     * Determines if file contains entry
     *
     * @param file  with text where each line represents an entry
     * @param entry candidate
     * @return if entry belongs to file
     */
    public static boolean contains(URI file, String entry) throws IOException {
        Path path = Paths.get(file);
        Trie trie = new Trie(new AlphabetEncoding(alphabet(path)));
        Files.lines(path).forEach(trie::insert);
        return trie.contains(entry);
    }

    private static Set<Character> alphabet(Path file) throws IOException {
        return Files.lines(file)
                .flatMap(line -> line.chars().mapToObj(i -> (char) i))
                .distinct()
                .collect(Collectors.toSet());
    }
}
