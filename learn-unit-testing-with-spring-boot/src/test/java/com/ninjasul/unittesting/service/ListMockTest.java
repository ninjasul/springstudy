package com.ninjasul.unittesting.service;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Log4j2
public class ListMockTest {

    private static final String NINJASUL = "ninjasul";
    private static final String SOME_STRING_1 = "SomeString1";
    private static final String SOME_STRING_2 = "SomeString2";
    List<String> list = mock(List.class);

    @Test
    public void size_basic() {
        when(list.size()).thenReturn(5);
        assertEquals(5, list.size());
    }

    @Test
    public void returnDifferentValues() {
        when(list.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, list.size());
        assertEquals(10, list.size());
    }

    @Test
    public void returnWithParameters() {

        when(list.get(0)).thenReturn(NINJASUL);
        assertEquals(NINJASUL, list.get(0));
        assertEquals(null, list.get(1));
    }

    @Test
    public void returnWithGenericParameters() {
        when(list.get(anyInt())).thenReturn(NINJASUL);

        assertEquals(NINJASUL, list.get(0));
        assertEquals(NINJASUL, list.get(1));
    }

    @Test
    public void verificationBasics() {
        String value1 = list.get(0);
        String value2 = list.get(1);

        verify(list).get(0);
        verify(list).get(1);
        verify(list, times(2)).get(anyInt());
        verify(list, atLeast(1)).get(anyInt());
        verify(list, atLeastOnce()).get(0);
        verify(list, atMost(2)).get(anyInt());
        verify(list, never()).get(2);
    }

    @Test
    public void argumentCapturing() {

        list.add(NINJASUL);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(list).add(captor.capture());

        assertEquals( NINJASUL, captor.getValue() );
    }

    @Test
    public void multipleArgumentCapturing() {

        list.add( SOME_STRING_1 );
        list.add( SOME_STRING_2 );

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(list, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();

        assertEquals( SOME_STRING_1, allValues.get(0) );
        assertEquals( SOME_STRING_2, allValues.get(1) );
    }

    @Test
    public void mocking() {
        ArrayList arrayListMock = mock(ArrayList.class);

        log.info(arrayListMock.get(0));     // null
        log.info(arrayListMock.size());     // 0

        arrayListMock.add("Test1");
        arrayListMock.add("Test2");

        // mock 에 item을 추가 해도 size는 여전히 0
        log.info(arrayListMock.size());     // 0

        when(arrayListMock.size()).thenReturn(5);
        log.info(arrayListMock.size());     // 5

    }

    @Test
    public void spying() {
        ArrayList arrayListSpy = spy(ArrayList.class);

        arrayListSpy.add("Test1");

        log.info(arrayListSpy.get(0));     // Test0
        log.info(arrayListSpy.size());     // 1

        arrayListSpy.add("Test2");
        arrayListSpy.add("Test3");

        // mock 에 item을 추가 해도 size는 여전히 0
        log.info(arrayListSpy.size());     // 3

        when(arrayListSpy.size()).thenReturn(5);
        log.info(arrayListSpy.size());     // 5

        arrayListSpy.add("Test4");
        log.info(arrayListSpy.size());     // 5

        verify(arrayListSpy).add("Test4");
    }
}
