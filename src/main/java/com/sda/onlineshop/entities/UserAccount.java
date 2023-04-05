package com.sda.onlineshop.entities;

import com.sda.onlineshop.enums.UserRole;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String name;
    @Nonnull
    private String address;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Cart cart;
    @OneToMany(mappedBy = "userAccount")
    private List<Order> orderList;
}
