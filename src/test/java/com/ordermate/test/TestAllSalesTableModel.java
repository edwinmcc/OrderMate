package com.ordermate.test;

import com.ordermate.components.model.AllSalesTableModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by edwin on 22/05/17.
 */
public class TestAllSalesTableModel extends BaseTestCase {


    private String[] columnNames=null;

    private AllSalesTableModel astm=null;

    @Before
    public void prepareForTestCase() {
        super.prepareForTestCase();
        columnNames = new String[] { " "," "," "," "," "};
        astm=new AllSalesTableModel(columnNames);
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
        super.cleanUp();
        columnNames=null;
        astm=null;
    }

}
