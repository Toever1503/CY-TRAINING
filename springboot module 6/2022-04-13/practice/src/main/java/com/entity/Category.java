package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "slug")
    private String slug;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cat_parent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Category catParent;

    @JsonManagedReference
    @OneToMany(mappedBy = "catParent", cascade = CascadeType.ALL)
    private java.util.List<Category> catChilds;

}
