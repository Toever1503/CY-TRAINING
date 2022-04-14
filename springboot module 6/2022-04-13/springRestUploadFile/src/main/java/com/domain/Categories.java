package com.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categories {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_slug")
    private String catSlug;

    @Column(name = "cat_order")
    private Integer catOrder;

    @ManyToOne
    @JoinColumn(name="cat_parent")
    private Categories catParent;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "catParent")
    private List<Categories> subCategories;
}
