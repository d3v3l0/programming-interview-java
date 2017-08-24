package com.github.tkachuko.programming.interview.search.trie.common.alphabet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AlphabetEncoding {

    private Map<Character, Integer> alphabet = new HashMap<>();

    public AlphabetEncoding(Set<Character> alphabet) {
        int i = 0;
        for (Character ch : alphabet) {
            this.alphabet.put(ch, i++);
        }
    }

    public AlphabetEncoding(String word) {
        this(word.chars().mapToObj(i -> (char) i).collect(Collectors.toSet()));
    }

    public AlphabetEncoding(Collection<String> words) {
        this(words.stream().flatMap(word -> word.chars().mapToObj(i -> (char) i)).collect(Collectors.toSet()));
    }

    public int charToIndex(char ch) {
        return alphabet.get(ch);
    }

    public int size() {
        return alphabet.size();
    }
}
