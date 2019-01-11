package com.ninjasul.unittesting.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
@Entity
public class Item {

    @Id
    int id;
    String name;
    int price;
    int quantity;

    @Transient
    int value;
}