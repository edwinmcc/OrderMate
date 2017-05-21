package com.ordermate.data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by edwin on 20/05/17.
 */
public class SaleItem {

    private int itemCount;
    private Product product;

    public SaleItem(Product product) {
        this.product=product;
        this.itemCount=1;
    }

    public Product getProduct() {
        return product;
    }

    public void setItemCount(int itemCount) {
        this.itemCount=itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public BigDecimal getPrice() {
        return product.getPrice().multiply(new BigDecimal(itemCount));
    }

    public boolean equals(Object o) {
        if(this==o) {
            return true;
        }
        if(o==null) {
            return false;
        }
        if(!(o instanceof SaleItem)) {
            return false;
        }
        SaleItem si=(SaleItem)o;
        return Objects.equals(product,si.getProduct());

    }

}
