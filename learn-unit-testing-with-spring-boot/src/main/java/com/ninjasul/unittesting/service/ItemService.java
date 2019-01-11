package com.ninjasul.unittesting.service;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item retrieveHardCodedItem() {
        return Item.builder()
                .id(1)
                .name("Ball")
                .price(10)
                .quantity(100)
                .build();
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = itemRepository.findAll();

        items.stream()
            .forEach( item -> item.setValue(item.getPrice() * item.getQuantity()) );

        return items;
    }


}