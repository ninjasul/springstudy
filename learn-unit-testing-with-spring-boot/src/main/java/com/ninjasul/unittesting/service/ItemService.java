package com.ninjasul.unittesting.service;

import com.ninjasul.unittesting.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public Item retrieveHardCodedItem() {
        return new Item( 1, "Ball", 10, 100 );
    }
}