package com.github.tkachuko.programming.interview.strings;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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

    /**
     * Generates all phone mnemonics using dictionary on cell phone keyboard.
     * In example 2 can be represented as A, B or C.
     *
     * @param number number as a string
     * @return all possible combinations of mnemonics
     */
    public static List<String> allPhoneMnemonics(String number) {
        List<String> result = new ArrayList<>();
        allPhoneMnemonicsHelper(number, "", result);
        return result;
    }

    /**
     * Utility recursion method for problem above
     *
     * @param remainingNumber remaining digits to generate sequences
     * @param current         current mnemonic under construction
     * @param acc             accumulator of all mnemonics
     */
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

    /**
     * Converts roman numbers to decimal ones
     *
     * @param romanNumber number in roman notation
     * @return number in decimal notation
     */
    public static int fromRomanNumber(String romanNumber) {
        int sum = 0;
        int last = romanNumber.length() - 1;
        for (int i = 1; i <= last; i++) {
            int preceding = ROMAN_NUMBER_PRIMITIVES.get(romanNumber.charAt(i - 1));
            int current = ROMAN_NUMBER_PRIMITIVES.get(romanNumber.charAt(i));
            if (preceding < current) {
                sum += (current - preceding);
            } else if (i == last) {
                sum += preceding + current;
            } else {
                sum += preceding;
            }
        }
        return sum;
    }

    private static final Map<Character, Integer> ROMAN_NUMBER_PRIMITIVES = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * Creates sinusoid from a string and prints it by concatenating upper level, middle and lower levels
     *
     * @param value input string
     * @return sinusoid string
     */
    public static String sinusoidString(String value) {
        StringBuilder builder = new StringBuilder(value.length());
        for (int i = 1; i < value.length(); i += 4) {
            builder.append(value.charAt(i));
        }
        for (int i = 0; i < value.length(); i += 2) {
            builder.append(value.charAt(i));
        }
        for (int i = 3; i < value.length(); i += 4) {
            builder.append(value.charAt(i));
        }
        return builder.toString();
    }

    /**
     * Behaves like a tail command in UNIX: extracts last lines (number specified as a parameter) from a given file.
     *
     * @param file              to extract lines from
     * @param numberOfLastLines number of lines to extract
     * @return lines from the end of a file
     * @throws IOException in case file could not be open for reading
     */
    public static String tail(File file, int numberOfLastLines) throws IOException {
        RandomAccessFile fileAccess = new RandomAccessFile(file, "r");

        StringBuilder result = new StringBuilder();
        long linesRead = 0;

        for (long filePointer = fileAccess.length() - 1; filePointer != -1; filePointer--) {
            fileAccess.seek(filePointer);

            char currentSymbol = (char) fileAccess.readByte();
            if (currentSymbol == '\n') {
                linesRead++;
            }
            if (linesRead == numberOfLastLines) {
                break;
            }
            result.append(currentSymbol);
        }
        return result.reverse().toString();
    }

    /**
     * Define if a string is a substring of a given string. Uses Rabin-Karp algorithm.
     *
     * @param str  possible substring
     * @param text string which may contain substring
     * @return if a string is a substring
     */
    public static boolean isSubstring(String str, String text) {
        if (str.length() > text.length()) return false;
        else if (str.length() == text.length()) return str.equals(text);

        Hashing.HashingData textHashData = Hashing.rollingHash(text, str.length());
        Hashing.HashingData strHashData = Hashing.rollingHash(str, str.length());

        int textHash = textHashData.hash;
        int strHash = strHashData.hash;

        for (int i = str.length(); i < text.length(); i++) {
            if (textHash == strHash && text.substring(i - str.length(), i).equals(str))
                return true;
            else
                textHash = Hashing.rollingHash(
                        text.charAt(i - str.length()),
                        text.charAt(i),
                        textHash,
                        strHashData.power
                );
        }

        return textHash == strHash;
    }
}
