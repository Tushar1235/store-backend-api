package com.ecommerce.EcommerceBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    private String password;
    private String gender;
    private String address;

    private Date date;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Roles> role = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = this.role.stream().map(roles -> new SimpleGrantedAuthority(roles.getRoleName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
