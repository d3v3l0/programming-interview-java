package com.github.tkachuko.origramming.interview.search;

import java.util.List;

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
            if (current > element) end -= 1;
            else if (current < element) start += 1;
            else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }
}
