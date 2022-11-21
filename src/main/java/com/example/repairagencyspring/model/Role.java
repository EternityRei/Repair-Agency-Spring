package com.example.repairagencyspring.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
