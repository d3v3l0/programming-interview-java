package com.github.tkachuko.origramming.interview.strings;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tkachuko.origramming.interview.strings.Strings.decodeExcelColumnNumber;
import static org.junit.Assert.assertEquals;

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
}
