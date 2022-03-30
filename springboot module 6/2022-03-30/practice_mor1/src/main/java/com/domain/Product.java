package com.domain;

public class Product {
    private Long Id;
    private String Name;
    private Integer Price;
    private Boolean Status;

    public Product() {
    }

    public Product(Long Id, String Name, Integer Price, Boolean Status) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Status = Status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Product{" + "Id=" + Id + ", Name=" + Name + ", Price=" + Price + ", Status=" + Status + '}';
    }

}
