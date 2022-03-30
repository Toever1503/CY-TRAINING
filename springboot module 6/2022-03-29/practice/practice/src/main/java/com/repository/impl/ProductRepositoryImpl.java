package com.repository.impl;

import com.domain.People;
import com.domain.Product;
import com.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static List<Product> List = new ArrayList<Product>();

    {
        List.add(new Product(1l, "Product 1", "Image 1", 95));
        List.add(new Product(2l, "Product 2", "Image 2", 753));
        List.add(new Product(3l, "Product 3", "Image 3", 52));
        List.add(new Product(4l, "Product 4", "Image 4", 25));
        List.add(new Product(5l, "Product 5", "Image 5", 745));
        List.add(new Product(6l, "Product 6", "Image 6", 125));
        List.add(new Product(7l, "Product 7", "Image 7", 74));
        List.add(new Product(8l, "Product 8", "Image 8", 63));
        List.add(new Product(9l, "Product 9", "Image 9", 12));
        List.add(new Product(10l, "Product 10", "Image 10", 15));
        List.add(new Product(11l, "Product 11", "Image 11", 23));
        List.add(new Product(12l, "Product 12", "Image 12", 57));
        List.add(new Product(13l, "Product 13", "Image 113", 74));
    }

    @Override
    public Product add(Product o) {
        if (List.contains(o))
            return null;
        else List.add(o);
        return o;
    }

    @Override
    public Product update(Product o) {
        int index = List.indexOf(o);
        if (index == -1)
            return null;
        List.set(index, o);
        return o;
    }

    @Override
    public Product findById(Long id) {
        Product p = null;
        for (int i = 0; i < List.size(); ++i) {
            if (id.equals(List.get(i).getId())) {
                p = List.get(i);
                break;
            }
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        return List;
    }

    @Override
    public boolean deleteById(Long id) {
        Product p = new Product();
        p.setId(id);
        List.remove(p);
        return true;
    }

    @Override
    public int sumTotalPricesOfProduct() {
        AtomicInteger total = new AtomicInteger(0);
        List.forEach(p -> {
            total.addAndGet(p.getPrice());
        });
        return total.get();
    }
}
