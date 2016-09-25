package com.github.tkachuko.origramming.interview.lists;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tkachuko.origramming.interview.lists.Lists.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    @Parameters
    public void shouldDetectCyclesInList(Node<Integer> list, boolean hasCycle, Integer cycleStartElement) {
        Node<Integer> cycleStart = cycleStart(list);
        if (hasCycle) {
            assertEquals(cycleStartElement, cycleStart.data);
        } else {
            assertNull(cycleStart);
        }
    }

    public static Object parametersForShouldDetectCyclesInList() {
        return new Object[]{
                new Object[]{cycledList(2, 5), true, 2},
                new Object[]{cycledList(5, 5), true, 5},
                new Object[]{cycledList(3, 5), true, 3},
                new Object[]{from(asList(1, 2, 3, 4, 5)), false, 0}
        };
    }

    private static Node<Integer> cycledList(int cycleStart, int size) {
        Node<Integer> head = new Node<>(1);
        Node<Integer> cycleNode = new Node<>(cycleStart);
        Node<Integer> current = head;
        for (int i = 2; i <= size; i++) {
            if (i == cycleStart) {
                current.tail = cycleNode;
            } else {
                current.tail = new Node<>(i);
            }
            current = current.tail;
        }
        current.tail = cycleNode;
        return head;
    }

    @Test
    @Parameters
    public void shouldRemoveKthLastElement(Node<Integer> list, int k, List<Integer> expected) {
        removeKthFromEnd(list, k);
        assertEquals(expected, toJavaList(list));
    }

    public static Object parametersForShouldRemoveKthLastElement() {
        return new Object[]{
                new Object[]{from(asList(0, 1, 2, 3, 4, 5)), 2, asList(0, 1, 2, 3, 5)},
                new Object[]{from(asList(0, 1, 2, 3, 4, 5)), 1, asList(0, 1, 2, 3, 4)},
                new Object[]{from(asList(0, 1, 2, 3, 4, 5)), 4, asList(0, 1, 3, 4, 5)},
                new Object[]{from(asList(0, 1, 2, 3, 4, 5)), 5, asList(0, 2, 3, 4, 5)}
        };
    }

    @Test
    @Parameters
    public void shouldRemoveDuplicatesFromSorted(Node<Integer> list, List<Integer> expected) {
        removeDuplicatesFromSorted(list);
        assertEquals(expected, toJavaList(list));
    }

    public static Object parametersForShouldRemoveDuplicatesFromSorted() {
        return new Object[]{
                new Object[]{from(asList(0, 1, 1, 2, 2, 2, 3, 4, 5, 5)), asList(0, 1, 2, 3, 4, 5)},
                new Object[]{from(asList(0, 0, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5)), asList(0, 1, 2, 3, 4, 5)}
        };
    }
}
