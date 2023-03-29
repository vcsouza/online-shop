package com.sda.onlineshop.entities;

import com.sda.onlineshop.enums.UserRole;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
}
