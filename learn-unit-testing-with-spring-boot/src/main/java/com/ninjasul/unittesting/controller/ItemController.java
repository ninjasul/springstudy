package com.ninjasul.unittesting.controller;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return Item.builder()
                    .id(1)
                    .name("Ball")
                    .price(10)
                    .quantity(100)
                .build();
    }

    @GetMapping("/item-from-service")
    public Item retrieveItemFromService() {
        return itemService.retrieveHardCodedItem();
    }

    @GetMapping("/all-items-from-database")
    public List<Item> retrieveAllItems() {
        return itemService.retrieveAllItems();
    }
}