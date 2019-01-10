package com.ninjasul.unittesting.business;

import com.ninjasul.unittesting.data.SomeDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {

    @InjectMocks
    SomeBusinessImpl someBusinessImpl;

    @Mock
    SomeDataService someDataService;

    @Test
    public void caculateSumUsingDataService_Basic() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] { 1, 2, 3 });

        // Then
        assertEquals( 6, someBusinessImpl.calculateSumUsingDataService() );
    }

    @Test
    public void caculateSumUsingDataService_OneData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] { 5 });

        // Then
        assertEquals( 5, someBusinessImpl.calculateSumUsingDataService());
    }

    @Test
    public void caculateSumUsingDataService_EmptyData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(new int [] {});

        // Then
        assertEquals( 0, someBusinessImpl.calculateSumUsingDataService());
    }

    @Test
    public void caculateSumUsingDataService_NullData() {

        // Given & When
        when(someDataService.retrieveAllData()).thenReturn(null);

        // Then
        assertEquals( 0, someBusinessImpl.calculateSumUsingDataService());

    }
}