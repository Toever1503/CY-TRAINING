package com.domain;

public class Category {
    private Long Id;
    private String CatName;
    private String CatSlug;
    private Integer CatOrder;
    private Category CatParent;
    private Boolean Status;

    public Category() {
    }

    public Category(Long id, String catName, String catSlug, Integer catOrder, Category catParent, Boolean status) {
        Id = id;
        CatName = catName;
        CatSlug = catSlug;
        CatOrder = catOrder;
        CatParent = catParent;
        Status = status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getCatSlug() {
        return CatSlug;
    }

    public void setCatSlug(String catSlug) {
        CatSlug = catSlug;
    }

    public Integer getCatOrder() {
        return CatOrder;
    }

    public void setCatOrder(Integer catOrder) {
        CatOrder = catOrder;
    }

    public Category getCatParent() {
        return CatParent;
    }

    public void setCatParent(Category catParent) {
        CatParent = catParent;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", CatName='" + CatName + '\'' +
                ", CatSlug='" + CatSlug + '\'' +
                ", CatOrder=" + CatOrder +
                ", CatParent=" + CatParent +
                ", Status=" + Status +
                '}';
    }
}
