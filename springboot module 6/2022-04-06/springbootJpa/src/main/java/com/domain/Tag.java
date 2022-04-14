package com.domain;

import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name= "join_clumn",
            joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {
            @JoinColumn(name = "user_id")} )
    private List<User> users;
}
