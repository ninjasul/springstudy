package com.ninjasul.unittesting.controller;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item( 1, "Ball", 10, 100 );
    }

    @GetMapping("/item-from-service")
    public Item itemFromService() {
        return itemService.retrieveHardCodedItem();
    }
}