package com.github.tkachuko.programming.interview.trie;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class TrieTest {

    @Property
    public void shouldContainAllAddedElements(String element) {
        Trie<String, Character> trie = Trie.ofStrings();
        trie.add(element);
        assertThat(trie.contains(element)).isTrue();
    }
}