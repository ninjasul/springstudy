package com.ninjasul.unittesting.controller;

import com.ninjasul.unittesting.model.Item;
import com.ninjasul.unittesting.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    ItemService itemService;

    @Test
    public void helloWorld_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Ball"))
                .andExpect(jsonPath("price").value(10))
                .andExpect(jsonPath("quantity").value(100))
                .andExpect(jsonPath("value").value(0))
                .andReturn();
    }

    @Test
    public void retrieveItemFromService() throws Exception {

        when(itemService.retrieveHardCodedItem()).thenReturn(
                Item.builder()
                    .id(1)
                    .name("Ball")
                    .price(10)
                    .quantity(100)
                    .value(0)
                    .build()
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-service")
                .accept(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Ball"))
                .andExpect(jsonPath("price").value(10))
                .andExpect(jsonPath("quantity").value(100))
                .andExpect(jsonPath("value").value(0))
                .andReturn();

    }

    @Test
    public void retrieveAllItems() throws Exception {

        when(itemService.retrieveAllItems()).thenReturn(
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

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Item2"))
                .andExpect(jsonPath("$[0].price").value(10))
                .andExpect(jsonPath("$[0].quantity").value(100))
                .andExpect(jsonPath("$[0].value").value(0))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[1].name").value("Item3"))
                .andExpect(jsonPath("$[1].price").value(20))
                .andExpect(jsonPath("$[1].quantity").value(20))
                .andExpect(jsonPath("$[1].value").value(0))
                .andReturn();

    }
}