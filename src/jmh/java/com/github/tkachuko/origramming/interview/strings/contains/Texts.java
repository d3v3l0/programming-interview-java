package com.github.tkachuko.origramming.interview.strings.contains;

import java.util.Random;

public interface Texts {

    String TEXT = hugeText();

    static String hugeText() {
        return textOfSize(Integer.MAX_VALUE / 10);
    }

    static String textOfSize(int size) {
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            builder.append(randomCharacter());
        }
        return builder.toString();
    }

    static char randomCharacter() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }
}
