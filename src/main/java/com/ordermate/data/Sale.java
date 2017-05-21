package com.ordermate.data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by edwin on 21/05/17.
 */
public class Sale {

    private List<SaleItem> items;
    private Date transactionTime;
    private BigDecimal saleValue;

    public Sale(List<SaleItem> items,BigDecimal saleValue) {
        this.items= Collections.unmodifiableList(items);
        this.saleValue=saleValue;
        transactionTime = new Date();
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

}
