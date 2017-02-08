package com.github.tkachuko.origramming.interview.sorting;

import com.github.tkachuko.origramming.interview.arrays.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.tkachuko.origramming.interview.sorting.Sorting.*;
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

    @Test
    @Parameters
    public static void shouldAddNewInterval(List<Interval> intervals,
                                            Interval addition,
                                            List<Interval> result) {
        assertThat(addInterval(intervals, addition)).isEqualTo(result);
    }

    public static Object parametersForShouldAddNewInterval() {
        return new Object[]{
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6), Interval.of(9, 10)),
                        Interval.of(3, 8),
                        Arrays.asList(Interval.of(1, 8), Interval.of(9, 10))
                },
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6), Interval.of(9, 10)),
                        Interval.of(3, 5),
                        Arrays.asList(Interval.of(1, 6), Interval.of(9, 10))
                },
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6), Interval.of(9, 10)),
                        Interval.of(0, 4),
                        Arrays.asList(Interval.of(0, 4), Interval.of(5, 6), Interval.of(9, 10))
                },
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6), Interval.of(9, 10)),
                        Interval.of(0, 10),
                        Arrays.asList(Interval.of(0, 10))
                }
        };
    }

    @Test
    @Parameters
    public static void shouldMergeOverlappingIntervals(List<Interval> intervals, List<Interval> merged) {
        assertThat(mergeIntervals(intervals)).isEqualTo(merged);
    }

    public static Object parametersForShouldMergeOverlappingIntervals() {
        return new Object[]{
                new Object[]{
                        Arrays.asList(
                                Interval.of(8, 10), Interval.of(7, 10), Interval.of(1, 2),
                                Interval.of(9, 10), Interval.of(11, 12)),
                        Arrays.asList(Interval.of(1, 2), Interval.of(7, 12))
                },
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(3, 4)),
                        Arrays.asList(Interval.of(1, 4))
                },
                new Object[]{
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6)),
                        Arrays.asList(Interval.of(1, 2), Interval.of(5, 6))
                }
        };
    }
}