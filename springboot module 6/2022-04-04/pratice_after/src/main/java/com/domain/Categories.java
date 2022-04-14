package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    @Column("id")
    private Long id;

    @Column("cat_name")
    private String catName;

    @Column("cat_slug")
    private String catSlug;

    @Column("cat_order")
    private Integer catOrder;

    @Column("cat_parent")
    private Long catParent;

    @Column("status")
    private Boolean status;
}
