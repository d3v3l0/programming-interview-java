package com.github.tkachuko.programming.interview.sorting;

import java.util.ArrayList;
import java.util.Comparator;
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

    public static class Interval {
        public final int left, right;

        public static Interval of(int left, int right) {
            return new Interval(left, right);
        }

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (left != interval.left) return false;
            return right == interval.right;

        }

        @Override
        public int hashCode() {
            int result = left;
            result = 31 * result + right;
            return result;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * Adds interval to the array of disjoint (non-overlapping) intervals
     *
     * @param intervals disjoint (non-overlapping)
     * @param addition  new interval to be added
     * @return disjoint (non-overlapping) intervals array with new interval added
     */
    public static List<Interval> addInterval(List<Interval> intervals, Interval addition) {
        List<Interval> result = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).left > addition.right) {
            result.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).left <= addition.right) {
            addition = new Interval(
                    Math.min(intervals.get(i).left, addition.left),
                    Math.max(intervals.get(i).right, addition.right)
            );
            ++i;
        }
        result.add(addition);

        result.addAll(intervals.subList(i, intervals.size()));
        return result;
    }

    /**
     * Converts any list with overlapping intervals to list of non-overlapping intervals
     *
     * @param intervals list with overlapping intervals
     * @return list of non-overlapping intervals
     */
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i2 -> i2.left));

        List<Interval> result = new ArrayList<>();
        Interval current = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            if (next.left <= current.right || next.left - current.right == 1) {
                current = new Interval(
                        Math.min(current.left, next.left),
                        Math.max(current.right, next.right)
                );
            } else {
                result.add(current);
                current = next;
            }
        }

        result.add(current);
        return result;
    }
}
