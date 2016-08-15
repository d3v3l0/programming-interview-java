package com.github.tkachuko.origramming.interview.lists;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static com.github.tkachuko.origramming.interview.lists.Lists.from;
import static com.github.tkachuko.origramming.interview.lists.Lists.toJavaList;
import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnitQuickcheck.class)
public class ListsProperties {

    @Property
    public void conversionsShouldBeOppositeOperations(Integer... values) {
        assertArrayEquals(values, toJavaList(from(values)).toArray());
    }
}
