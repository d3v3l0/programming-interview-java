package com.github.tkachuko.origramming.interview.sorting;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

    public static List<Integer> mergeTwoSorted(List<Integer> first, List<Integer> second) {
        if (first.size() < second.size()) return mergeTwoSorted(second, first);
        List<Integer> result = new ArrayList<>(first.size() + second.size());

        int positionInFirst = 0, positionInSecond = 0;
        while (positionInFirst < first.size() && positionInSecond < second.size()) {

            int elementInFirst = first.get(positionInFirst);
            int elementInSecond = second.get(positionInSecond);

            if (elementInFirst < elementInSecond) {
                result.add(elementInFirst);
                ++positionInFirst;
            } else if (elementInFirst > elementInSecond) {
                result.add(elementInSecond);
                ++positionInSecond;
            } else {
                result.add(elementInFirst);
                result.add(elementInSecond);
                ++positionInFirst;
                ++positionInSecond;
            }
        }

        while (positionInFirst != first.size()) {
            result.add(first.get(positionInFirst));
            ++positionInFirst;
        }

        return result;
    }
}
