package com.github.tkachuko.programming.interview.search.trie.common;

import java.io.Serializable;

public interface TrieNode extends Serializable {

    long serialVersionUID = 1L;

    TrieNode[] children();
}
