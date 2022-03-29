package com.day_2022_03_21.pratice;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer Id;
    private String Name;
    private Double Price;
    private String PictureName;
    private String PictureUrl;

    public Product() {

    }
    public Product(Integer id, String name, Double price, String pictureName, String pictureUrl) {
        Id = id;
        Name = name;
        Price = price;
        PictureName = pictureName;
        PictureUrl = pictureUrl;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getPictureName() {
        return PictureName;
    }

    public void setPictureName(String pictureName) {
        PictureName = pictureName;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", PictureName='" + PictureName + '\'' +
                ", PictureUrl='" + PictureUrl + '\'' +
                '}';
    }
}
