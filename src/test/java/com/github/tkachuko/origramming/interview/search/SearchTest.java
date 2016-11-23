package com.github.tkachuko.origramming.interview.search;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.tkachuko.origramming.interview.search.Search.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SearchTest {

    @Test
    @Parameters
    public void shouldFindFirstOccurrenceOfElementIn(List<Integer> list, int element) {
        assertThat(indexOfInSorted(list, element)).isEqualTo(list.indexOf(element));
    }

    private static Object parametersForShouldFindFirstOccurrenceOfElementIn() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4, 5), 3},
                new Object[]{asList(1, 2, 3, 3, 4, 5), 3},
                new Object[]{asList(1, 1, 2, 3, 3, 4, 5), 1},
                new Object[]{asList(1, 1, 2, 3, 3, 4, 5, 5), 5},
                new Object[]{asList(1, 1, 2, 3, 3, 4, 5), 5},
                new Object[]{asList(1, 1, 2, 3, 3, 4, 5), -1},
                new Object[]{asList(1, 1, 2, 3, 3, 4, 5), 10}
        };
    }

    @Test
    @Parameters
    public void shouldFindLocalMinimaIn(List<Integer> list, int minima) {
        assertThat(localMinima(list)).isEqualTo(minima);
    }

    private static Object parametersForShouldFindLocalMinimaIn() {
        return new Object[]{
                new Object[]{asList(1, 3, 2, 4, 5), 2},
                new Object[]{asList(1, 2, 3, 4, 5, 6), -1},
                new Object[]{asList(1, 2, 3, 2, 5, 6, 7), 2},
                new Object[]{asList(1, -8, 3, 5, 6), -8},
                new Object[]{asList(1, -10, 3, 4, 5, 6), -10}
        };
    }

    @Test
    @Parameters
    public void shouldFindFirstGreaterThanPivotInSorted(List<Integer> list, int pivot) {
        int last = list.lastIndexOf(pivot);
        int greater = (last == list.size() - 1) ? pivot : list.get(last + 1);
        assertThat(firstGreaterInSorted(list, pivot)).isEqualTo(greater);
    }

    private static Object parametersForShouldFindFirstGreaterThanPivotInSorted() {
        return new Object[]{
                new Object[]{asList(1, 2, 4, 5), 2},
                new Object[]{asList(1, 2, 2, 4, 5), 2},
                new Object[]{asList(1, 2, 2, 4, 5), 4},
                new Object[]{asList(1, 2, 2, 4, 5), 1},
                new Object[]{asList(1, 2, 2, 4, 4, 55), 55},
                new Object[]{asList(1, 1, 2, 2, 4, 4, 5, 5), 1}
        };
    }

    @Test
    @Parameters
    public void shouldFindFirstAndLastOccurrenceOfElementInList(List<Integer> list, int element) {
        assertThat(occurrenceIntervalInSorted(list, element))
                .containsExactly(list.indexOf(element), list.lastIndexOf(element));
    }

    private static Object parametersForShouldFindFirstAndLastOccurrenceOfElementInList() {
        return new Object[]{
                new Object[]{asList(1, 1, 1, 1, 1, 1, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5), 1},
                new Object[]{asList(1, 1, 1, 1, 1, 1, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5), 2},
                new Object[]{asList(1, 1, 1, 1, 1, 1, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5), 4},
                new Object[]{asList(1, 1, 1, 1, 1, 1, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5), 5}
        };
    }

    @Test
    @Parameters
    public void shouldFindElementTheSameAsIndex(List<Integer> list) {
        Set<Integer> result = IntStream.range(0, list.size())
                .filter(i -> list.get(i) == i)
                .boxed()
                .collect(Collectors.toSet());
        assertThat(elementAsIndexInSorted(list)).isIn(result);
    }

    private static Object parametersForShouldFindElementTheSameAsIndex() {
        return new Object[]{
                new Object[]{asList(0, 2, 3, 5)},
                new Object[]{asList(0, 1, 2, 3)},
                new Object[]{asList(-2, 0, 2, 3, 6, 8, 80, 90)}
        };
    }
}