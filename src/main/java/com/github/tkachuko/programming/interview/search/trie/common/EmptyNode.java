package com.github.tkachuko.programming.interview.search.trie.common;

import java.util.Collections;
import java.util.Map;

public class EmptyNode implements TrieNode {

    private static final TrieNode INSTANCE = new EmptyNode();

    public static TrieNode instance() {
        return INSTANCE;
    }

    private EmptyNode() {
    }

    @Override
    public Map<Character, TrieNode> children() {
        return Collections.EMPTY_MAP;
    }
}
