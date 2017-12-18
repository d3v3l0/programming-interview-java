package com.github.tkachuko.programming.interview.trie;

import java.util.ArrayList;
import java.util.List;

public class TrieNode<T> {

    private final T value;
    private final List<TrieNode<T>> children;

    public TrieNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public List<TrieNode<T>> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrieNode<?> trieNode = (TrieNode<?>) o;

        return value.equals(trieNode.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
