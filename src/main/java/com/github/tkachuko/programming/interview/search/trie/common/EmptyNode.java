package com.github.tkachuko.programming.interview.search.trie.common;

import java.io.Serializable;

public class EmptyNode implements TrieNode, Serializable {

    private static final long serialVersionUID = 1L;

    private static final TrieNode INSTANCE = new EmptyNode();

    public static TrieNode instance() {
        return INSTANCE;
    }

    private EmptyNode() {
    }

    @Override
    public TrieNode[] children() {
        return null;
    }
}
