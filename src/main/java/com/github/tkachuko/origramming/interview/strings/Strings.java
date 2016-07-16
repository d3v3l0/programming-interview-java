package com.github.tkachuko.origramming.interview.strings;

import java.util.*;

public class Strings {

    /**
     * Returns a decoded form of excel column count i.e. 'AA', 'ZZZZ' 'AB'
     *
     * @param value excel encoded column number
     * @return decoded value
     */
    public static long decodeExcelColumnNumber(String value) {
        long decoded = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            int currentInDecimalBase = value.charAt(i) - 'A' + 1;
            long power = (long) Math.pow(26, value.length() - i - 1);
            decoded += power * currentInDecimalBase;
        }
        return decoded;
    }

    /**
     * Reverse characters in a string
     *
     * @param string original string
     * @return string with reversed characters
     */
    public static String reverse(String string) {
        char[] characters = string.toCharArray();
        reverseRange(characters, 0, characters.length - 1);
        return new String(characters);
    }

    /**
     * Reverses certain range in a string
     *
     * @param characters characters array
     * @param start      start index of reversed substring
     * @param end        end index of reversed substring
     */
    public static void reverseRange(char[] characters, int start, int end) {
        for (int i = start, j = end; i <= j; i++, j--) {
            char tmp = characters[i];
            characters[i] = characters[j];
            characters[j] = tmp;
        }
    }

    /**
     * Returns sentence with words in opposite order i.e bob is cool => cool is bob
     *
     * @param sentence sentence with words
     * @return sentence with words in reverse order
     */
    public static String reverseWordsInSentence(String sentence) {
        char[] characters = sentence.toCharArray();
        reverseRange(characters, 0, characters.length - 1);
        int wordStart = 0;
        for (int i = 0; i < characters.length - 1; i++) {
            if (Character.isSpaceChar(characters[i])) {
                reverseRange(characters, wordStart, i - 1);
                wordStart = i + 1;
            }
        }
        if (wordStart != 0) {
            reverseRange(characters, wordStart, characters.length - 1);
        } else {
            reverseRange(characters, 0, characters.length - 1);
        }
        return new String(characters);
    }

    public static List<String> allPhoneMnemonics(String number) {
        List<String> result = new ArrayList<>();
        allPhoneMnemonicsHelper(number, "", result);
        return result;
    }

    private static void allPhoneMnemonicsHelper(String remainingNumber, String current, List<String> acc) {
        if (remainingNumber.isEmpty()) acc.add(current);
        else {
            for (String element : PHONE_KEYBOARD.get(remainingNumber.charAt(0))) {
                allPhoneMnemonicsHelper(remainingNumber.substring(1), current + element, acc);
            }
        }
    }

    private static final Map<Character, List<String>> PHONE_KEYBOARD = new HashMap<Character, List<String>>() {{
        put('2', Arrays.asList("A", "B", "C"));
        put('3', Arrays.asList("D", "E", "F"));
        put('4', Arrays.asList("G", "H", "I"));
        put('5', Arrays.asList("J", "K", "L"));
        put('6', Arrays.asList("M", "N", "O"));
        put('7', Arrays.asList("P", "Q", "R", "S"));
        put('8', Arrays.asList("T", "U", "V"));
        put('9', Arrays.asList("W", "X", "Y", "Z"));
    }};
}
