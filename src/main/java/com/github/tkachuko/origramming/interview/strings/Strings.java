package com.github.tkachuko.origramming.interview.strings;

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
}
