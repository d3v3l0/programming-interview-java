package com.github.tkachuko.origramming.interview.lists;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.tkachuko.origramming.interview.lists.Lists.*;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ListsProperties {

    @Property
    public void conversionsShouldBeOppositeOperations(List<Integer> values) {
        assertEquals(values, toJavaList(from(values)));
    }

    @Property
    public void resultingListShouldBeAlwaysSorted(List<Integer> first, List<Integer> second) {
        Collections.sort(first);
        Collections.sort(second);

        List<Integer> result = toJavaList(sortedFromTwoSorted(from(first), from(second)));
        first.addAll(second);

        Collections.sort(first);

        assertEquals(first, result);
    }

    @Property
    public void shouldReverseListRecursively(List<Integer> elements) {
        List<Integer> reversed = new ArrayList<>(elements);
        Collections.reverse(reversed);

        assertEquals(reversed, toJavaList(reverseRecursively(from(elements))));
    }

    @Property
    public void shouldReverseListNonRecursively(List<Integer> elements) {
        List<Integer> reversed = new ArrayList<>(elements);
        Collections.reverse(reversed);

        assertEquals(
                toJavaList(reverseWithoutRecursion(from(elements))),
                toJavaList(reverseRecursively(from(elements)))
        );
    }
}
