package com.github.tkachuko.programming.interview.graphs;

import org.junit.Test;

import static com.github.tkachuko.programming.interview.graphs.Graphs.containsCelebrity;
import static com.github.tkachuko.programming.interview.graphs.Graphs.topologicalSort;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphsTest {

    @Test
    public void graphShouldContainCelebrity() {
        boolean[][] graph = {
                new boolean[]{false, false, false, false},
                new boolean[]{true, false, false, false},
                new boolean[]{true, true, false, false},
                new boolean[]{true, false, false, false}
        };
        assertTrue(containsCelebrity(graph));
    }

    @Test
    public void graphShouldNotContainCelebrity() {
        boolean[][] graph = {
                new boolean[]{true, false, true, false},
                new boolean[]{false, false, true, false},
                new boolean[]{true, true, false, false},
                new boolean[]{true, false, false, false}
        };
        assertFalse(containsCelebrity(graph));
    }

    @Test
    public void customGraphShouldBeSortedTopologically() {
        boolean[][] graph = {
                new boolean[]{false, true, false, false, false, false},
                new boolean[]{false, false, true, true, false, false},
                new boolean[]{false, false, false, true, false, false},
                new boolean[]{false, false, false, false, true, false},
                new boolean[]{false, true, false, false, false, false},
                new boolean[]{false, true, false, false, false, false}
        };
        assertThat(topologicalSort(graph)).containsExactly(5, 0, 1, 2, 3, 4);
    }

    @Test
    public void geeksGraphShouldBeSortedTopologically() {
        boolean[][] graph = {
                new boolean[]{false, false, false, false, false, false},
                new boolean[]{false, false, false, false, false, false},
                new boolean[]{false, false, false, true, false, false},
                new boolean[]{true, false, false, false, false, false},
                new boolean[]{true, true, false, false, false, false},
                new boolean[]{true, false, true, false, false, false}
        };
        assertThat(topologicalSort(graph)).containsExactly(5, 4, 2, 3, 1, 0);
    }
}