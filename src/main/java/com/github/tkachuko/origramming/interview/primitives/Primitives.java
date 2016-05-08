package com.github.tkachuko.origramming.interview.primitives;

public class Primitives {

    /**
     * Returns the parity of long word. Parity = number of bits equal to '1'.
     * Hint: use XoR and group word by 32 bits to speed up operation on processor level
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
}
