package com.github.tkachuko.programming.interview.search.trie.common;

import java.io.Serializable;

public class NonEmptyNode implements TrieNode, Serializable {

    private static final long serialVersionUID = 1L;

    private final char value;
    private final TrieNode[] children;

    public NonEmptyNode(char value, int max) {
        this.value = value;
        children = new TrieNode[max];
    }

    @Override
    public TrieNode[] children() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonEmptyNode that = (NonEmptyNode) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
