package com.ninjasul.unittesting.controller;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ItemControllerIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ItemService itemService;

    @Test
    public void contextLoads() {
        List<Item> resultItems = Arrays.asList(restTemplate.getForObject("/all-items-from-database", Item[].class));
        List<Item> serviceItems = itemService.retrieveAllItems();

        for( int i = 0, length = resultItems.size(); i < length; ++i ) {
            assertEquals(serviceItems.get(i), resultItems.get(i) );
        }
    }
}
