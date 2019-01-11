package com.ninjasul.unittesting.service;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void retrieveAllItems() {
        when(itemRepository.findAll()).thenReturn(
            Arrays.asList(
                Item.builder()
                        .id(2)
                        .name("Item2")
                        .price(10)
                        .quantity(100)
                        .build(),

                Item.builder()
                        .id(3)
                        .name("Item3")
                        .price(20)
                        .quantity(20)
                        .build()
            )
        );

        List<Item> items = itemService.retrieveAllItems();

        assertEquals( 1000, items.get(0).getValue() );
        assertEquals( 400, items.get(1).getValue() );
    }
}