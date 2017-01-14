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

    /**
     * Finds out if input can ve permuted so that it forms a palindrome
     *
     * @param input any string
     * @return if string can be permuted to be a palindrome
     */
    public static boolean canFormPalindrome(String input) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (Character character : input.toCharArray()) {
            frequency.computeIfAbsent(character, k -> 0);
            frequency.computeIfPresent(character, (k, v) -> v + 1);
        }
        int odds = 0;
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() % 2 != 0 && ++odds > 1) return false;
        }
        return true;
    }

    /**
     * Checks if anonymous letter can be composed from the magazine article (like in movies)
     *
     * @param letter   text of letter
     * @param magazine text of magazine article
     * @return if letter can be composed from magazine article
     */
    public static boolean isAnonymousLetterCreatedFrom(String letter, String magazine) {
        Map<Character, Integer> count = new HashMap<>();
        for (char character : letter.toCharArray()) {
            count.computeIfAbsent(character, k -> 0);
            count.computeIfPresent(character, (k, v) -> v + 1);
        }
        for (char character : magazine.toCharArray()) {
            Integer current = count.get(character);
            if (current != null && current > 0) {
                count.put(character, current - 1);
                current--;
            }
            if (current != null && current == 0) count.remove(character);
        }
        return count.isEmpty();
    }
}
