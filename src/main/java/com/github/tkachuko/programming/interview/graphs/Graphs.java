package com.github.tkachuko.programming.interview.graphs;

public class Graphs {

    /**
     * Celebrity is someone who is known by everybody. Does this graph contain celebrity?
     *
     * @param graph represented as matrix
     * @return is this graph has node that everyone else is connected to
     */
    public static boolean containsCelebrity(boolean[][] graph) {
        int first = 0, second = graph.length - 1;
        while (first < second) {
            if (graph[first][second]) first++;
            else second--;
        }

        for (int i = 0; i < graph.length; i++) {
            if (i != first && graph[first][i]) return false;
        }

        return true;
    }
}
