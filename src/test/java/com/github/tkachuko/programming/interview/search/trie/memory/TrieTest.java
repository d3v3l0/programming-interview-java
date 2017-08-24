package com.github.tkachuko.programming.interview.search.trie.memory;

import com.github.tkachuko.programming.interview.search.trie.common.alphabet.AlphabetEncoding;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TrieTest {

    @Test
    public void shouldInsertAndContainAverageString() {
        String word = "hello";

        Trie trie = new Trie(new AlphabetEncoding(word));
        trie.insert(word);

        assertThat(trie.contains(word)).isTrue();
    }

    @Test
    public void shouldNotContainSubstring() {
        String word = "hello";

        Trie trie = new Trie(new AlphabetEncoding(word));
        trie.insert(word);

        assertThat(trie.contains(word.substring(0, word.length() - 2))).isFalse();
    }

    @Test
    public void shouldInsertAndContainSomeNumberOfStrings() {
        int size = 1000;

        Set<String> words = new HashSet<>();

        IntStream.rangeClosed(1, size)
                .mapToObj(i -> UUID.randomUUID().toString())
                .forEach(words::add);

        Trie trie = new Trie(new AlphabetEncoding(words));

        words.forEach(trie::insert);

        assertThat(words.stream().allMatch(trie::contains)).isTrue();
        assertThat(trie.contains(UUID.randomUUID().toString())).isFalse();
    }
}