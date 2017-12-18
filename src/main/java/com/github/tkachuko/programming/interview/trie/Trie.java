package com.github.tkachuko.programming.interview.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Trie<V, T> {

    private final Function<V, Iterable<T>> decomposition;
    private List<TrieNode<T>> root;

    private Trie(Function<V, Iterable<T>> decomposition) {
        this.root = new ArrayList<>();
        this.decomposition = decomposition;
    }

    public static <V, T> Trie<V, T> from(Function<V, Iterable<T>> decomposition) {
        return new Trie<>(decomposition);
    }

    public static Trie<String, Character> ofStrings() {
        return new Trie<>(
                str -> str.chars().mapToObj(i -> (char) i).collect(Collectors.toList())
        );
    }

    public void add(V value) {
        Iterable<T> elements = decomposition.apply(value);
        List<TrieNode<T>> level = root;
        for (T element : elements) {
            TrieNode<T> existing = findOnLevel(element, level);
            if (existing != null) level = existing.getChildren();
            else {
                TrieNode<T> node = new TrieNode<>(element);
                level.add(node);
                level = node.getChildren();
            }
        }
    }

    public boolean contains(V value) {
        Iterable<T> elements = decomposition.apply(value);
        List<TrieNode<T>> level = root;
        for (T element : elements) {
            TrieNode<T> node = findOnLevel(element, level);
            if (node == null) return false;
            else level = node.getChildren();
        }
        return true;
    }

    private TrieNode<T> findOnLevel(T element, List<TrieNode<T>> level) {
        return level.stream()
                .filter(node -> node.getValue().equals(element))
                .findAny()
                .orElse(null);
    }
}
