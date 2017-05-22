package com.ordermate.components.gui;

import com.ordermate.components.listener.ItemChangeListener;
import com.ordermate.components.listener.ItemSelectedListener;
import com.ordermate.data.Product;
import com.ordermate.data.ProductGroup;
import com.ordermate.data.SaleItem;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 20/05/17.
 */
public class ProductsPanel extends JPanel implements ItemChangeListener<ProductGroup> {

    private GridLayout gridLayout;
    private List<Product> productList;
    private List<ProductPanel> panelProductsList;
    private ItemSelectedListener<SaleItem> itemSelectedListener;

    public ProductsPanel() {

    }

    public void init() {

        gridLayout = new GridLayout(0,4, 30,30);
        setLayout(gridLayout);
        panelProductsList=new ArrayList<ProductPanel>();

    }

    private void removeOldProducts() {
        for(ProductPanel pp : panelProductsList) {
            remove(pp);
        }
        panelProductsList.clear();
        revalidate();
        repaint();
    }

    public void setItemSelectedListener(ItemSelectedListener<SaleItem> itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    private void addNewProducts(List<Product> newProducts) {
        for(Product p : newProducts) {
            ProductPanel pp=new ProductPanel(p);
            panelProductsList.add(pp);
            add(pp);
            pp.setItemSelectedListener(itemSelectedListener);
        }
        for(int i=newProducts.size();i<16;i++) {
            Product p=new Product(" ",new BigDecimal("0"),"/images/default1.png");
            ProductPanel pp=new ProductPanel(p);
            panelProductsList.add(pp);
            add(pp);
        }
    }

    public void showProducts(List<Product> productList) {
        removeOldProducts();
        addNewProducts(productList);
    }

    @Override
    public void itemChanged(ProductGroup productGroup) {
        List<Product> productList = productGroup.getProducts();
        showProducts(productList);
        revalidate();
    }
}
