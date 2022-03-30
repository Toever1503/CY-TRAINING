package com.domain;

public class Product {
    private Long Id;
    private String Name;
    private String Image;
    private Integer Price;

    public Product() {
    }

    public Product(Long id, String name, String image, Integer price) {
        Id = id;
        Name = name;
        Image = image;
        Price = price;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.Id.equals(((Product) o).getId());
    }

}
