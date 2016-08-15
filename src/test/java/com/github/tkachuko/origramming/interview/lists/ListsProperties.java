package com.github.tkachuko.origramming.interview.lists;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.tkachuko.origramming.interview.lists.Lists.*;
import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnitQuickcheck.class)
public class ListsProperties {

    @Property
    public void conversionsShouldBeOppositeOperations(Integer... values) {
        assertArrayEquals(values, toJavaList(from(values)).toArray());
    }

    @Property
    public void resultingListShouldBeAlwaysSorted(Integer[] first, Integer[] second) {
        Arrays.sort(first);
        Arrays.sort(second);

        List<Integer> result = toJavaList(sortedFromTwoSorted(from(first), from(second)));
        Object[] concatenated = concatenate(first, second);
        Arrays.sort(concatenated);

        assertArrayEquals(concatenated, result.toArray());
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] concatenate(T[] first, T[] second) {
        List<T> firstList = new ArrayList<>(Arrays.asList(first));
        List<T> secondList = Arrays.asList(second);

        firstList.addAll(secondList);

        return (T[]) firstList.toArray();
    }
}
