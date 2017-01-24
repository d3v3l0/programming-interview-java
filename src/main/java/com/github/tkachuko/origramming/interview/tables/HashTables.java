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

    /**
     * Generic LRU cache implementation with three operations: insert, lookup and erase
     *
     * @param <K> type of key
     * @param <V> type of value
     */
    public static class LRUCache<K, V> {

        private final Map<K, V> cache;

        public LRUCache(int capacity) {
            this.cache = new LinkedHashMap<K, V>() {
                @Override
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    return size() > capacity;
                }
            };
        }

        /**
         * Inserts value for the key in case it is not present in the cache.
         * If value is already in the cache mapping is not changed but is being put on top of the cache.
         *
         * @param key   in the cache
         * @param value that should be inserted in case no mapping exists
         */
        public void insert(K key, V value) {
            V currentValue = cache.get(key);
            if (currentValue != null) moveToFront(key, currentValue);
            else cache.put(key, value);
        }

        /**
         * Get current value mapped to the given key as an Optional
         *
         * @param key in the cache
         * @return optional that contains value mapped to the key in the cache
         */
        public Optional<V> lookup(K key) {
            Optional<V> result = Optional.ofNullable(cache.get(key));
            result.ifPresent(value -> moveToFront(key, value));
            return result;
        }

        /**
         * Removes mapping for the given key in the cache
         *
         * @param key to remove mapping of
         */
        public void erase(K key) {
            cache.remove(key);
        }

        /**
         * Moves entry so that it is becomes the most recently used and won't be deleted
         *
         * @param key   of the cache
         * @param value associated with the key
         */
        private void moveToFront(K key, V value) {
            cache.remove(key);
            cache.put(key, value);
        }
    }

    /**
     * Creates ISBN book LRU cache where key is ISBN in integer form and value is price of the item
     *
     * @param capacity of the cache. Adding more items than capacity will make least used items be deleted.
     * @return ISBN LRU cache with prices
     */
    public LRUCache<Integer, Integer> isbnCache(int capacity) {
        return new LRUCache<>(capacity);
    }

    /**
     * Finds the shortest sub-sequence containing all key words
     *
     * @param words    sequence of words
     * @param keyWords to find in words
     * @return indexes of shortest sub-sequence that contains all keywords
     */
    public static int[] shortestSubSequenceCovering(List<String> words, Set<String> keyWords) {
        LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
        keyWords.forEach(word -> dict.put(word, null));

        int numberOfKeyWordsSeenSoFar = 0;

        int start = -1, end = -1;
        for (int wordIndexInSequence = 0; wordIndexInSequence < words.size(); wordIndexInSequence++) {
            String word = words.get(wordIndexInSequence);

            if (dict.containsKey(word)) {
                Integer lastKnownPosition = dict.get(word);
                if (lastKnownPosition == null) {
                    numberOfKeyWordsSeenSoFar++;
                }

                dict.remove(word);
                dict.put(word, wordIndexInSequence);
            }

            if (numberOfKeyWordsSeenSoFar == keyWords.size()) {
                Integer startIndexOfCurrentSequence = firstValue(dict);
                int lengthOfCurrentSequence = wordIndexInSequence - startIndexOfCurrentSequence;

                int lengthOfShortestSequence = end - start;
                if ((start == -1 && end == -1) || lengthOfCurrentSequence < lengthOfShortestSequence) {
                    start = startIndexOfCurrentSequence;
                    end = wordIndexInSequence;
                }
            }
        }
        return new int[]{start, end};
    }

    private static <K, V> V firstValue(LinkedHashMap<K, V> map) {
        return map.entrySet().stream().findFirst().map(Map.Entry::getValue).orElse(null);
    }

    /**
     * Finds the length of longest sub-array that contains distinct values from given array
     *
     * @param array with duplicates
     * @return length of longest sub-array that contains distinct values from given array
     */
    public static int lengthOfLongestSubArrayWithDistinctValues(List<Integer> array) {
        Map<Integer, Integer> visitedElements = new HashMap<>();
        int startIndexOfResult = 0, lengthOfResult = 0;

        for (int i = 0; i < array.size(); i++) {
            Integer lastKnownOccurrence = visitedElements.put(array.get(i), i);
            if (lastKnownOccurrence != null && lastKnownOccurrence >= startIndexOfResult) {
                lengthOfResult = Math.max(lengthOfResult, i - startIndexOfResult);
                startIndexOfResult = lastKnownOccurrence + 1;
            }
        }

        return Math.max(lengthOfResult, array.size() - startIndexOfResult);
    }
}
