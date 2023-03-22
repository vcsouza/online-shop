package com.sda.onlineshop.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Nonnull
    private String name;
    private String description;
    @Nonnull
    private double price;
    @Nonnull
    private String category;
    @Nonnull
    private Integer unitsInStock;

}
