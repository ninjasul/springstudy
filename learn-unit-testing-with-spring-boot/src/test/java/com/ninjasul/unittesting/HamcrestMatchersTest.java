package com.ninjasul.unittesting;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class HamcrestMatchersTest {

    @Test
    public void learning() {

        List<Integer> numbers = Arrays.asList(12, 15, 45);

        assertThat( numbers, hasSize(3));
        assertThat( numbers, hasItems(12, 15));
        assertThat( numbers, hasItems(45));
        assertThat( numbers, everyItem(greaterThan(10)));
        assertThat( numbers, everyItem(lessThan(100)));
        assertThat( numbers, hasItem(lessThan(100)));

        assertThat( "", isEmptyString());
        assertThat( "ABCDE", containsString("BCD"));
        assertThat( "ABCDE", startsWith("ABC"));
        assertThat( "ABCDE", endsWith("CDE"));
    }
}