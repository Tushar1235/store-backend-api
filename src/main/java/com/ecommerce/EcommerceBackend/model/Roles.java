package com.ecommerce.EcommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    private int roleId;

    private String roleName;

    @ManyToMany(mappedBy = "role")
    Set<User> user = new HashSet<>();
}
