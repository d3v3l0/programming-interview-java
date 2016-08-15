package com.github.tkachuko.origramming.interview.primitives;

public class Primitives {

    /**
     * Returns the parity of long word. Parity = number of bits equal to '1'.
     * Hint: use XoR and group word by 32 bits to speed up operation on processor level
     *
     * @param word long word
     * @return 0 or 1
     */
    public static short parity(long word) {
        word ^= word >>> 32;
        word ^= word >>> 16;
        word ^= word >>> 8;
        word ^= word >>> 4;
        word ^= word >>> 2;
        return (short) (word & 0x01);
    }

    /**
     * Swaps bits on positions i and j in long word x.
     * Hint: when do you need to swap?
     *
     * @param x long word
     * @param i position
     * @param j position
     * @return long word with swapped bits at position i and j
     */
    public static long swapBits(long x, int i, int j) {
        if ((x >>> i & 1) != (x >>> j & 1)) {
            // Bits are different, we need to swap
            return x ^ ((1 << j) | (1 << i));
        }
        return x;
    }

    /**
     * Reverses bits in word
     *
     * @param word bit word
     * @return word with reversed bits
     */
    public static long reverseBits(long word) {
        long reversed = 0;
        while (word != 0) {
            reversed <<= 1;
            reversed |= (word & 1);
            word >>= 1;
        }
        return reversed;
    }

    /**
     * Returns integer which has the same weight (number of bits set) and closest to the given number x
     *
     * @param x number provided
     * @return integer which has the same number of bits set and closest to the given number x
     */
    public static int closestWithTheSameWeight(int x) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (((x >> i) & 1) != ((x >> (i + 1)) & 1)) {
                return x ^ (1 << i | 1 << (i + 1));
            }
        }
        return -1;
    }

    /**
     * Reverses the digits in given number
     *
     * @param number input to be reversed
     * @return number with digits in reverse order
     */
    public static int reverseDigits(int number) {
        int result = 0;
        while (number != 0) {
            result *= 10;
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    /**
     * Determines if number in binary representation is a palindrome
     *
     * @param number input number
     * @return if number in binary representation is palindrome
     */
    public static boolean isBinaryPalindrome(long number) {
        if (number < 0) return false;
        int numberOfBits = (int) (Math.log(number) / Math.log(2)) + 1;
        int lastBitIndex = numberOfBits - 1;
        long mask = 1 << lastBitIndex;
        for (int i = 0; i < numberOfBits; i += 2) {
            long lastBit = (number >> (lastBitIndex - i)) & 1;
            long firstBit = number & 1;
            if (lastBit != firstBit) return false;
            number ^= mask;
            number >>= 1;
            mask >>= 2;
        }
        return true;
    }

    /**
     * Determines if number in decimal representation is a palindrome
     *
     * @param number input number
     * @return if number is decimal palindrome
     */
    public static boolean isDecimalPalindrome(long number) {
        if (number < 0) return false;
        int numberOfDigits = (int) Math.log10(number) + 1;
        long mask = (long) Math.pow(10, numberOfDigits - 1);
        for (int i = 0; i < numberOfDigits / 2; i++) {
            if (number / mask != number % 10) return false;
            number %= mask;
            number /= 10;
            mask /= 100;
        }
        return true;
    }

    /**
     * Defines if n is prime number
     *
     * @param n candidate to be a prime number
     * @return if n is prime number
     */
    public static boolean isPrime(int n) {
        int limit = (int) Math.ceil(Math.sqrt(n));
        if (n == limit) return true;
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
