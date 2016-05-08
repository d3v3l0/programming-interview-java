package com.github.tkachuko.origramming.interview.primitives;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tkachuko.origramming.interview.primitives.Primitives.parity;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PrimitivesTest {

    @Test
    @Parameters
    public void parityShouldBeOne(long word) {
        assertEquals(1, parity(word));
    }

    @Test
    @Parameters
    public void parityShouldBeZero(long word) {
        assertEquals(0, parity(word));
    }

    public Object parametersForParityShouldBeOne() {
        return new Object[]{
                new Object[]{123654156234156243L},
                new Object[]{1},
                new Object[]{4},
                new Object[]{12},
                new Object[]{Long.MAX_VALUE - 1},
        };
    }

    public Object parametersForParityShouldBeZero() {
        return new Object[]{
                new Object[]{123654156234156242L},
                new Object[]{2},
                new Object[]{5},
                new Object[]{13},
                new Object[]{Long.MAX_VALUE},
        };
    }
}
