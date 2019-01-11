package com.ninjasul.unittesting.model;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
public class Item {
    int id;
    String name;
    int price;
    int quantity;
}