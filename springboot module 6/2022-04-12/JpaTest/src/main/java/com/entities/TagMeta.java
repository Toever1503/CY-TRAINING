package com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tag_meta")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagMeta {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meta_key")
    private String metaKey;

    @Column(name = "meta_value")
    private String metaValue;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="tag_id", columnDefinition = "bigint default 1")
    private Tag tagParent;
}
