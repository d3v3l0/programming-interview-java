package com.github.tkachuko.programming.interview.search.trie.common;

import java.util.Map;

public interface TrieNode {

    Map<Character, TrieNode> children();
}
