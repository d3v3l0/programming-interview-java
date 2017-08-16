package com.github.tkachuko.programming.interview.search.trie.memory;

import com.github.tkachuko.programming.interview.search.trie.common.EmptyNode;
import com.github.tkachuko.programming.interview.search.trie.common.NonEmptyNode;
import com.github.tkachuko.programming.interview.search.trie.common.TrieNode;
import com.github.tkachuko.programming.interview.search.trie.common.TrieSpec;

import java.util.HashMap;
import java.util.Map;

public class Trie implements TrieSpec {

    private final Map<Character, TrieNode> root = new HashMap<>();

    @Override
    public void insert(String word) {
        if (word == null || word.isEmpty()) return;

        Map<Character, TrieNode> level = root;

        for (int i = 0; i < word.length(); i++) {
            char key = word.charAt(i);

            level = level
                    .compute(key, (k, v) -> v == null ? new NonEmptyNode(k) : v)
                    .children();

            if (i == word.length() - 1) level.put(null, EmptyNode.instance());
        }
    }

    @Override
    public boolean contains(String word) {
        if (word == null || word.isEmpty()) return false;

        Map<Character, TrieNode> level = root;

        for (int i = 0; i < word.length(); i++) {
            TrieNode node = level.get(word.charAt(i));

            if (node == null) return false;
            else level = node.children();
        }

        return level.containsValue(EmptyNode.instance());
    }
}
