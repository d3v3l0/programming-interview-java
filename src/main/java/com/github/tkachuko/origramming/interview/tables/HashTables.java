package com.github.tkachuko.origramming.interview.tables;

import java.util.*;

public class HashTables {

    /**
     * Partitions a set of words into groups of strings that are anagrams
     *
     * @param words set of words
     * @return partitions of words that are anagrams
     */
    public static Collection<Set<String>> groupByAnagrams(Set<String> words) {
        Map<String, Set<String>> table = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            table.computeIfAbsent(key, k -> new HashSet<>());
            table.computeIfPresent(key, (k, value) -> {
                value.add(word);
                return value;
            });
        }
        return table.values();
    }
}
