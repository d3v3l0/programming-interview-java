package com.github.tkachuko.origramming.interview.tables;

import com.github.tkachuko.origramming.interview.arrays.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.tkachuko.origramming.interview.tables.HashTables.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class HashTablesTest {

    @Test
    public void shouldGroupWordsIntoAnagrams() {
        Set<String> words = new HashSet<String>() {{
            addAll(Arrays.asList(
                    "debitcard", "elvis", "silent", "badcredit",
                    "lives", "freedom", "listen", "levis", "money"
            ));
        }};
        assertThat(groupByAnagrams(words)).containsExactlyInAnyOrder(
                new HashSet<String>() {{
                    add("debitcard");
                    add("badcredit");
                }},
                new HashSet<String>() {{
                    add("elvis");
                    add("lives");
                    add("levis");
                }},
                new HashSet<String>() {{
                    add("silent");
                    add("listen");
                }},
                new HashSet<String>() {{
                    add("money");
                }},
                new HashSet<String>() {{
                    add("freedom");
                }}
        );
    }

    @Test
    @Parameters
    public void shouldDefineIfPalindromeCanBeFormed(String string, boolean canBePalindrome) {
        assertThat(canFormPalindrome(string)).isEqualTo(canBePalindrome);
    }

    public static Object parametersForShouldDefineIfPalindromeCanBeFormed() {
        return new Object[]{
                new Object[]{"edified", true},
                new Object[]{"mini", false}
        };
    }

    @Test
    @Parameters
    public void shouldDetermineIfLetterCanBeComposedFromMagazine(String letter, String magazine, boolean isComposable) {
        assertThat(isAnonymousLetterCreatedFrom(letter, magazine)).isEqualTo(isComposable);
    }

    public static Object parametersForShouldDetermineIfLetterCanBeComposedFromMagazine() {
        return new Object[]{
                new Object[]{"scary message", "gess me a rascy", true},
                new Object[]{"another scary message", "some fashion stuff, yaky", false}
        };
    }

    @Test
    @Parameters
    public void shouldFindSmallestSubsetThatCoversTextWithKeywords(List<String> words,
                                                                   Set<String> keywords,
                                                                   int[] indexes) {
        assertThat(smallestSubSequenceContaining(words, keywords)).isEqualTo(indexes);
    }

    public static Object parametersForShouldFindSmallestSubsetThatCoversTextWithKeywords() {
        return new Object[]{
                new Object[]{
                        Arrays.asList("banana", "slam", "brute", "something", "banana"),
                        Arrays.asSet("banana", "something"),
                        new int[]{3, 4}
                },
                new Object[]{
                        Arrays.asList("banana", "slam", "brute", "something"),
                        Arrays.asSet("banana", "something"),
                        new int[]{0, 3}
                }
        };
    }
}