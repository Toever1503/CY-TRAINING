package com.domain;

import java.util.Date;

public class Product {
    private Long Id;
    private String Name;
    private Integer OldPrice;
    private Integer Price;
    private String Image;
    private Category Category;
    private Integer Quantity;
    private Boolean Status;
    private Date CreatedDate;
    private User CreatedBy;

    public Product() {
    }

    public Product(Long id, String name, Integer oldPrice, Integer price, String image, com.domain.Category category, Integer quantity, Boolean status, Date createdDate, User createdBy) {
        Id = id;
        Name = name;
        OldPrice = oldPrice;
        Price = price;
        Image = image;
        Category = category;
        Quantity = quantity;
        Status = status;
        CreatedDate = createdDate;
        CreatedBy = createdBy;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        OldPrice = oldPrice;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        this.Category = category;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public User getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(User createdBy) {
        CreatedBy = createdBy;
    }
}
