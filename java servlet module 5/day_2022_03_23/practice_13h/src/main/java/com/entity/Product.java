package com.entity;

public class Product {
    private Long Id;
    private String Name;
    private Double Price;
    private String Description;
    private String Image;
    private Double OldPrice;
    private String Color;
    private String Material;
    private String Style;
    private String Season;
    private Integer Year;

    public Product() {
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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Double getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        OldPrice = oldPrice;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", price=" + Price +
                ", Description='" + Description + '\'' +
                ", Image='" + Image + '\'' +
                ", OldPrice=" + OldPrice +
                ", Color='" + Color + '\'' +
                ", Material='" + Material + '\'' +
                ", Style='" + Style + '\'' +
                ", Season='" + Season + '\'' +
                ", Year=" + Year +
                '}';
    }
}
