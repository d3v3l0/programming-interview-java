package com.github.tkachuko.origramming.interview.search;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tkachuko.origramming.interview.search.Search.indexOfInSorted;
import static com.github.tkachuko.origramming.interview.search.Search.localMinima;
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
}