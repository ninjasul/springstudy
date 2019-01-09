package com.ninjasul.unittesting.business;

import com.ninjasul.unittesting.data.SomeDataService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SomeBusinessMockTest {

    SomeBusinessImpl someBusinessImpl;
    SomeDataService someDataService;

    @Before
    public void setUp() {
        someBusinessImpl = new SomeBusinessImpl();
        someDataService = mock(SomeDataService.class);
        someBusinessImpl.setSomeDataService(someDataService);
    }

    @Test
    public void caculateSumUsingDataService_Basic() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] { 1, 2, 3 });
        int actualResult = someBusinessImpl.calculateSumUsingDataService();

        // Then
        verify(someDataService, times(1)).retrieveAllData();
        assertEquals( 6, actualResult );
    }

    @Test
    public void caculateSumUsingDataService_OneData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] { 5 });
        int actualResult = someBusinessImpl.calculateSumUsingDataService();

        // Then
        verify(someDataService, times(1)).retrieveAllData();
        assertEquals( 5, actualResult );
    }

    @Test
    public void caculateSumUsingDataService_EmptyData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] {});
        int actualResult = someBusinessImpl.calculateSumUsingDataService();

        // Then
        verify(someDataService, times(1)).retrieveAllData();
        assertEquals( 0, actualResult );
    }

    @Test
    public void caculateSumUsingDataService_NullData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(null);
        int actualResult = someBusinessImpl.calculateSumUsingDataService();

        // Then
        verify(someDataService, times(1)).retrieveAllData();
        assertEquals( 0, actualResult );

    }
}