package com.ordermate.components.model;

import com.ordermate.data.Product;
import com.ordermate.data.ProductGroup;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by edwin on 19/05/17.
 */
public class GroupsModel extends DefaultListModel<ProductGroup> {

    private List<ProductGroup> productGroups=null;

    public GroupsModel() {

    }

    private List<ProductGroup> getAllGroupsFromDB() {

        List<ProductGroup> productGroups=new ArrayList<ProductGroup>();
        ProductGroup pg1=new ProductGroup("Burger");
        Product p1=new Product("Burger",new BigDecimal(6.13),"/images/burger.jpg");
        Product p2=new Product("Cheese Burger",new BigDecimal(7.50),"/images/cburger.jpg");
        pg1.addProduct(p1);
        pg1.addProduct(p2);


        ProductGroup pg2=new ProductGroup("Chips");
        Product p3=new Product("Chips Large",new BigDecimal(4.50),"/images/chips.jpg");
        Product p4=new Product("Chips Small",new BigDecimal(3.50),"/images/chips.jpg");

        pg2.addProduct(p3);
        pg2.addProduct(p4);

        ProductGroup pg3=new ProductGroup("Drinks");
        Product p5=new Product("Coke",new BigDecimal(4.50),"/images/coke.jpg");
        Product p6=new Product("Diet Coke",new BigDecimal(3.50),"/images/dcoke.jpg");
        Product p7=new Product("Fanta",new BigDecimal(4.50),"/images/fanta.jpg");
        Product p8=new Product("Pepsi",new BigDecimal(3.50),"/images/pepsi.jpg");

        pg3.addProduct(p5);
        pg3.addProduct(p6);
        pg3.addProduct(p7);
        pg3.addProduct(p8);

        productGroups.add(pg1);
        productGroups.add(pg2);
        productGroups.add(pg3);

        return productGroups;
    }

    public void init() {
        this.productGroups=getAllGroupsFromDB();
        removeAllElements();
        for(ProductGroup group : productGroups) {
            addElement(group);
        }
    }


}
