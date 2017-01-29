package com.github.tkachuko.origramming.interview.sorting;

import com.github.tkachuko.origramming.interview.arrays.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.tkachuko.origramming.interview.sorting.Sorting.mergeTwoSorted;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SortingTest {

    @Test
    @Parameters
    public static void shouldMergeTwoSortedArrays(List<Integer> first, List<Integer> second) {
        assertThat(mergeTwoSorted(first, second))
                .isEqualTo(Stream.concat(first.stream(), second.stream()).sorted().collect(Collectors.toList()));
    }

    public static Object parametersForShouldMergeTwoSortedArrays() {
        return new Object[]{
                new Object[]{
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7)
                },
                new Object[]{
                        Arrays.asList(-1, 0, 4, 5, 6, 7),
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7)
                },
                new Object[]{
                        Arrays.asList(-1, 0, 4),
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7)
                },
                new Object[]{
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(-1, 0, 4)
                }
        };
    }

}