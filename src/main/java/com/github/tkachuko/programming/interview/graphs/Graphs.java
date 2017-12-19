package com.github.tkachuko.programming.interview.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * Topologically sort given graph
     *
     * @param adjacency matrix of graph
     * @return topologically sorted
     */
    public static List<Integer> topologicalSort(boolean[][] adjacency) {
        Stack<Integer> stack = new Stack<>();

        boolean visited[] = new boolean[adjacency.length];
        for (int i = 0; i < adjacency.length; i++) {
            if (!visited[i]) topologicalVisit(adjacency, i, visited, stack);
        }

        List<Integer> sorted = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) sorted.add(stack.pop());
        return sorted;
    }

    private static void topologicalVisit(boolean[][] adjacency, int currentNode,
                                         boolean visited[], Stack<Integer> stack) {
        visited[currentNode] = true;
        for (int i = 0; i < adjacency[currentNode].length; i++) {
            if (!visited[i] && adjacency[currentNode][i])
                topologicalVisit(adjacency, i, visited, stack);
        }

        stack.push(currentNode);
    }
}
