package com.ordermate.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 19/05/17.
 */
public class ProductGroup {

    private Long id;
    private String groupName;
    private List<Product> products;

    public ProductGroup() {
        products=new ArrayList<>();
    }

    public ProductGroup(String groupName) {
        this();
        this.groupName=groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void addProduct(Product p) {
        if(products.contains(p)) {
            return;
        }
        this.products.add(p);
    }

    public void removeProduct(Product p) {
        if(products.contains(p)) {
            products.remove(p);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
