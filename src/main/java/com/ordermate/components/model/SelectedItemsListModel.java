package com.ordermate.components.model;

import com.ordermate.data.SaleItem;
import com.ordermate.utils.Utility;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by edwin on 20/05/17.
 */
public class SelectedItemsListModel extends DefaultListModel<SaleItem> {

    public void contentsChanged(int index) {
        fireContentsChanged(this,index,index);
    }

    public BigDecimal getTotalValue() {
        BigDecimal totalValue=new BigDecimal("0.00");
        for(int i=0;i<size();i++) {
            SaleItem saleItem=getElementAt(i);
            totalValue=totalValue.add(saleItem.getPrice());
        }
        totalValue = Utility.roundToNearest5Cents(totalValue);
        return totalValue;
    }

    public java.util.List<SaleItem> getSaleItemsList() {
        java.util.List<SaleItem> itemsList=new ArrayList<>();
        for(int i=0;i<size();i++) {
            SaleItem saleItem=getElementAt(i);
            itemsList.add(saleItem);
        }
        return Collections.unmodifiableList(itemsList);
    }

}
