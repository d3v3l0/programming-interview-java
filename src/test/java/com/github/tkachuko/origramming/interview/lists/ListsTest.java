package com.github.tkachuko.origramming.interview.lists;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tkachuko.origramming.interview.lists.Lists.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ListsTest {

    @Test
    @Parameters
    public void shouldReverseRangeInList(List<Integer> original, int from, int to, List<Integer> expected) {
        assertEquals(expected, toJavaList(reverseRange(from(original), from, to)));
    }

    public static Object parametersForShouldReverseRangeInList() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4, 5), 2, 4, asList(1, 4, 3, 2, 5)},
                new Object[]{asList(1, 2, 3, 4, 5), 3, 4, asList(1, 2, 4, 3, 5)},
                new Object[]{asList(1, 2, 3, 4, 5), 3, 5, asList(1, 2, 5, 4, 3)},
                new Object[]{asList(1, 2, 3, 4, 5), 1, 4, asList(4, 3, 2, 1, 5)},
                new Object[]{asList(1, 2, 3, 4, 5), 1, 3, asList(3, 2, 1, 4, 5)},
                new Object[]{asList(1, 2, 3, 4, 5), 1, 5, asList(5, 4, 3, 2, 1)},
                new Object[]{asList(1, 2, 3, 4, 5, 6, 7, 8), 2, 7, asList(1, 7, 6, 5, 4, 3, 2, 8)}
        };
    }
}
