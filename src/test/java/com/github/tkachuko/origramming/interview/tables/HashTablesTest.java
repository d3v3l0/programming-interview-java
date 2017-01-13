package com.github.tkachuko.origramming.interview.tables;

import com.github.tkachuko.origramming.interview.arrays.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static com.github.tkachuko.origramming.interview.tables.HashTables.canFormPalindrome;
import static com.github.tkachuko.origramming.interview.tables.HashTables.groupByAnagrams;
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
}