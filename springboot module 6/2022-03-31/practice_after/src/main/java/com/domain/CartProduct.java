package com.domain;

public class CartProduct {
    private int Quantity;
    private Product Product;

    public CartProduct() {
    }

    public CartProduct(int Quantity, Product product) {
        this.Quantity = Quantity;
        this.Product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        this.Product = product;
    }

    @Override
    public String toString() {
        return "MyCart{" + "Quantity=" + Quantity + ", product=" + Product + '}';
    }
}
