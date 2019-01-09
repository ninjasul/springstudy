package com.ninjasul.unittesting.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SomeBusinessTest {

    SomeBusinessImpl someBusinessImpl;

    @Before
    public void setUp() throws Exception {
        someBusinessImpl = new SomeBusinessImpl();
    }

    @Test
    public void calculateSum_ThreeNumbers() {

        // Given & When
        int actualResult = someBusinessImpl.calculateSum( new int[] { 1, 2, 3 } );

        // Then
        assertEquals( 6, actualResult );
    }

    @Test
    public void calculateSumWithEmptyData() {

        // Given & When
        int actualResult = someBusinessImpl.calculateSum( new int[] {} );

        // Then
        assertEquals( 0, actualResult );
    }

    @Test
    public void calculateSumWithNullData() {

        // Given & When
        int actualResult = someBusinessImpl.calculateSum( null );

        // Then
        assertEquals( 0, actualResult );
    }
}