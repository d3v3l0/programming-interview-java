package com.github.tkachuko.programming.interview.search.trie.common;

import java.io.Serializable;
import java.util.Map;

public interface TrieNode extends Serializable {

    long serialVersionUID = 1L;

    Map<Character, TrieNode> children();
}
