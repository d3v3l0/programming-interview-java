package com.github.tkachuko.programming.interview.graphs;

import org.junit.Test;

import static com.github.tkachuko.programming.interview.graphs.Graphs.containsCelebrity;
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
}