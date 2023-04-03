package com.sda.onlineshop.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    //this is the object that goes to the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String name;
    private String description;
    @Nonnull
    private double price;
    @Nonnull
    private String category;
    @Nonnull
    private Integer unitsInStock;
    @Lob
    @Column(columnDefinition = "blob")
    private byte[] img;

    @OneToMany(mappedBy = "product")
    private List<CartEntry> cartEntryList;

}
