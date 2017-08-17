package com.github.tkachuko.programming.interview.search.trie.common;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NonEmptyNode implements TrieNode, Serializable {

    private static final long serialVersionUID = 1L;

    private final char value;
    private final Map<Character, TrieNode> children;

    public NonEmptyNode(char value) {
        this.value = value;
        children = new HashMap<>();
    }

    @Override
    public Map<Character, TrieNode> children() {
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

    public static void main(String[] args) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("/tmp/test.bin"));
        stream.writeObject(new NonEmptyNode('1'));
        stream.close();
        System.out.println(stream);
    }
}
