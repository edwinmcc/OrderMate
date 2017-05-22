package com.ordermate;

import com.ordermate.components.model.AllSalesTableModel;
import com.ordermate.data.Product;
import com.ordermate.data.Sale;
import com.ordermate.data.SaleItem;
import com.ordermate.utils.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by edwin on 22/05/17.
 */
public class TestAllSalesTableModel {

    private Product p1=new Product("Burger",new BigDecimal(6.13),"/images/burger.jpg");
    private Product p2=new Product("Cheese Burger",new BigDecimal(7.50),"/images/cburger.jpg");
    private Product p3=new Product("Chips Large",new BigDecimal(4.50),"/images/chips.jpg");
    private Product p4=new Product("Chips Small",new BigDecimal(3.50),"/images/chips.jpg");
    private List<Product> products;
    private Sale firstSale;
    private Sale secondSale;

    private String[] columnNames={ " "," "," "," "," "};

    private AllSalesTableModel astm=new AllSalesTableModel(columnNames);


    private void createProductsList() {
        products=new ArrayList<Product>();

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    private void createFirstSale() {
        SaleItem si1=new SaleItem(p1);
        si1.setItemCount(2);
        SaleItem si2 = new SaleItem(p2);
        si2.setItemCount(2);

        List<SaleItem> firstSaleItems = new ArrayList<>();
        firstSaleItems.add(si1);
        firstSaleItems.add(si2);
        BigDecimal result=p1.getPrice().multiply(new BigDecimal(2)).add(p2.getPrice().multiply(new BigDecimal(2)));
        result = Utility.roundToNearest5Cents(result);
        firstSale = new Sale(firstSaleItems,result);
    }

    private void createSecondSale() {
        SaleItem si1=new SaleItem(p3);
        si1.setItemCount(3);
        SaleItem si2 = new SaleItem(p4);
        si2.setItemCount(4);

        List<SaleItem> secondSaleItems = new ArrayList<>();
        secondSaleItems.add(si1);
        secondSaleItems.add(si2);
        BigDecimal result=p1.getPrice().multiply(new BigDecimal(3)).add(p2.getPrice().multiply(new BigDecimal(4)));
        result = Utility.roundToNearest5Cents(result);
        secondSale = new Sale(secondSaleItems,result);

    }

    @Before
    public void prepareForTestCase() {
        createProductsList();
        createFirstSale();
        createSecondSale();
    }

    @Test
    public void verifyFirstSale() {
        astm.add(firstSale);
        assertEquals(astm.getTotalSales(),firstSale.getSaleValue());
        astm.removeRow(0);
    }

    @Test
    public void verifySecondSale() {

        astm.add(firstSale);
        astm.add(secondSale);
        assertEquals(astm.getTotalSales(),firstSale.getSaleValue().add(secondSale.getSaleValue()));
        astm.removeRow(1);
        astm.removeRow(0);

    }

    @After
    public void cleanUp() {

        p1=null;
        p2=null;
        p3=null;
        p4=null;
        products = null;
        firstSale= null;
        secondSale = null;
        columnNames=null;
        astm=null;

    }

}
