package com.github.tkachuko.origramming.interview.strings;

public class Hashing {

    private static final int MOD = 997, BASE = 26;

    public static class HashingData {
        public final int hash;
        public final int power;

        public HashingData(int hash, int power) {
            this.hash = hash;
            this.power = power;
        }
    }

    public static final HashingData rollingHash(String string, int limit) {
        int power = 1;
        int hash = 0;
        for (int i = 0; i < limit; i++) {
            power = i > 0 ? power * BASE % MOD : 1;
            hash = (hash * BASE + string.charAt(i)) % MOD;
        }
        return new HashingData(hash < 0 ? hash + MOD : hash, power);
    }

    public static final int rollingHash(char drop, char add, int previousHash, int previousPower) {
        int hash = previousHash;
        hash -= ((drop * previousPower) % MOD);
        if (hash < 0) hash += MOD;
        hash = (hash * BASE + add) % MOD;
        return hash;
    }
}
