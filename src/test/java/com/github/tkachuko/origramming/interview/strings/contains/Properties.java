package com.github.tkachuko.origramming.interview.strings.contains;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.Random;

import static com.github.tkachuko.origramming.interview.strings.Strings.isSubstring;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class Properties {

    @Property(trials = 5000)
    public void shouldBehaveLikeJavaContainsForPositiveCase(@When(satisfies = "#_.length() > 1000") String text,
                                                            @InRange(minInt = 1, maxInt = 400) int splitPoint,
                                                            @InRange(minInt = 1, maxInt = 400) int substringSize) {
        String str = text.substring(splitPoint, splitPoint + substringSize);
        assertEquals(text.contains(str), isSubstring(str, text));
    }

    @Property(trials = 5000)
    public void shouldBehaveLikeJavaContainsForNegativeCase(@When(satisfies = "#_.length() > 1000") String text,
                                                            @InRange(minInt = 1, maxInt = 400) int substringSize) {
        String str = randomString(substringSize);
        assertEquals(text.contains(str), isSubstring(str, text));
    }

    @Property(trials = 5000)
    public void shouldBehaveLikeJavaContainsForHalfOfOriginalString(String text) {
        String substring = text.substring(text.length() / 2, text.length());
        assertEquals(text.contains(substring), isSubstring(substring, text));
    }

    private static String randomString(int size) {
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            builder.append(randomCharacter());
        }
        return builder.toString();
    }

    private static char randomCharacter() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    public static void main(String[] args) {
        System.out.println(isSubstring("!!!!", "Hello world!!!!"));
    }
}
