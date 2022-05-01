package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="product_import_details")
public class ParcelImportDetail {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "import_quantity")
    private Long quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_import")
    private Product productImport;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private ParcelImport parcelImport;

}
