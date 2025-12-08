package com.musicstore.bluevelvet.infrastructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name="category_of_product")
@IdClass(CategoryProductLinksId.class)
public class CategoryProductLinks {

    @Id
    private Long category_id;
    @Id
    private Long product_id;
    @ManyToOne
    @PrimaryKeyJoinColumn(name="category_id", referencedColumnName="id")
    private Category category;
    @ManyToOne
    @PrimaryKeyJoinColumn(name="product_id", referencedColumnName="id")
    private Product product;
}
