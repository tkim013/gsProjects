package com.genspark.babygotbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9]+$")
    private String email;

    @NotBlank
    private String password;

    private boolean enabled;

    @ManyToMany(cascade = {
            CascadeType.ALL
    },
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "user_groups",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"
            ))
    private Set<Group> userGroups= new HashSet<>();

    public void addUserGroups(Group group){
        userGroups.add(group);
        group.getUsers().add(this);
    }

    public void removeUserGroups(Group group){
        userGroups.remove(group);
        group.getUsers().remove(this);
    }
}

