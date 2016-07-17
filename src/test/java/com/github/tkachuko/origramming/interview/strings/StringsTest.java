package com.github.tkachuko.origramming.interview.strings;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.github.tkachuko.origramming.interview.strings.Strings.*;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class StringsTest {

    @Test
    @Parameters
    public void shouldDecodeExcelColumn(String excelColumn, int decoded) {
        assertEquals(decodeExcelColumnNumber(excelColumn), decoded);
    }

    public Object parametersForShouldDecodeExcelColumn() {
        return new Object[]{
                new Object[]{"A", 1},
                new Object[]{"AA", 27},
                new Object[]{"ZZ", 702}
        };
    }

    @Test
    @Parameters
    public void shouldReverseString(String string, String reversed) {
        assertEquals(reversed, reverse(string));
    }

    public Object parametersForShouldReverseString() {
        return new Object[]{
                new Object[]{"A", "A"},
                new Object[]{"AB", "BA"},
                new Object[]{"ZZABBZ123", "321ZBBAZZ"}
        };
    }

    @Test
    @Parameters
    public void shouldReverseRangeInString(char[] string, int start, int end, char[] reversed) {
        reverseRange(string, start, end);
        assertArrayEquals(reversed, string);
    }

    public Object parametersForShouldReverseRangeInString() {
        return new Object[]{
                new Object[]{"ABSSA".toCharArray(), 0, 2, "SBASA".toCharArray()},
                new Object[]{"ZZABBZ123".toCharArray(), 3, 5, "ZZAZBB123".toCharArray()}
        };
    }

    @Test
    @Parameters
    public void shouldReverseWordsInSentence(String string, String wordsReversed) {
        assertEquals(wordsReversed, reverseWordsInSentence(string));
    }

    public Object parametersForShouldReverseWordsInSentence() {
        return new Object[]{
                new Object[]{"Alice likes Bob", "Bob likes Alice"},
                new Object[]{"Hello World", "World Hello"},
                new Object[]{"SingleWord", "SingleWord"},
                new Object[]{"Reverse Words In Sentence", "Sentence In Words Reverse"}
        };
    }

    @Test
    @Parameters
    public void shouldGenerateAllPhoneMnemonics(String number, List<String> shouldContain) {
        assertTrue(allPhoneMnemonics(number).containsAll(shouldContain));
    }

    public Object parametersForShouldGenerateAllPhoneMnemonics() {
        return new Object[]{
                new Object[]{"2", Arrays.asList("A", "B", "C")},
                new Object[]{"2276696", Arrays.asList("ACRONYM", "ABPOMZN")}
        };
    }

    @Test
    @Parameters
    public void shouldDecodeRomanNumber(String romanNumber, int number) {
        assertEquals(number, fromRomanNumber(romanNumber));
    }

    public Object parametersForShouldDecodeRomanNumber() {
        return new Object[]{
                new Object[]{"XXXXXIIIIIIIII", 59},
                new Object[]{"LVIIII", 59},
                new Object[]{"LIX", 59}
        };
    }

    @Test
    @Parameters
    public void shouldCreateSinusoidString(String in, String sinusoid) {
        assertEquals(sinusoid, sinusoidString(in));
    }

    public Object parametersForShouldCreateSinusoidString() {
        return new Object[]{
                new Object[]{"Hello World", "e lHloWrdlo"}
        };
    }
}
