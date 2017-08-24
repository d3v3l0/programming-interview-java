package com.github.tkachuko.programming.interview.search.trie.memory;

import com.github.tkachuko.programming.interview.search.trie.common.EmptyNode;
import com.github.tkachuko.programming.interview.search.trie.common.NonEmptyNode;
import com.github.tkachuko.programming.interview.search.trie.common.TrieNode;
import com.github.tkachuko.programming.interview.search.trie.common.TrieSpec;
import com.github.tkachuko.programming.interview.search.trie.common.alphabet.AlphabetEncoding;

import java.io.Serializable;

public class Trie implements TrieSpec, Serializable {

    private static final long serialVersionUID = 1L;

    private final TrieNode[] root;
    private final AlphabetEncoding alphabet;

    public Trie(AlphabetEncoding alphabet) {
        this.alphabet = alphabet;
        this.root = new TrieNode[alphabet.size()];
    }

    @Override
    public void insert(String word) {
        if (word == null || word.isEmpty()) return;

        TrieNode[] level = root;

        for (int i = 0; i < word.length(); i++) {
            char key = word.charAt(i);

            int index = alphabet.charToIndex(key);
            if (level[index] == null) {
                level[index] = new NonEmptyNode(key, alphabet.size());
            }
            level = level[index].children();

            if (i == word.length() - 1) level[index] = EmptyNode.instance();
        }
    }

    @Override
    public boolean contains(String word) {
        if (word == null || word.isEmpty()) return false;

        TrieNode[] level = root;

        for (int i = 0; i < word.length(); i++) {
            int index = alphabet.charToIndex(word.charAt(i));
            TrieNode node = level[index];

            if (node == null) return false;
            else level = node.children();
        }

        return level[alphabet.charToIndex(word.charAt(word.length() - 1))] == EmptyNode.instance();
    }
}
