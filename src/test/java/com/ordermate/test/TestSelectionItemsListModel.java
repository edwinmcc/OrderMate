package com.ordermate.test;

import com.ordermate.components.model.SelectedItemsListModel;
import com.ordermate.data.SaleItem;
import com.ordermate.test.BaseTestCase;
import com.ordermate.utils.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by edwin on 22/05/17.
 */
public class TestSelectionItemsListModel extends BaseTestCase {

    private SelectedItemsListModel silm=null;

    @Before
    public void prepareForTestCase() {
        super.prepareForTestCase();
    }

    @Test
    public void Test_SelectedItemsListModel_TotalValue_Transaction1() {
        silm = new SelectedItemsListModel();
        BigDecimal result=new BigDecimal("0.00");
        for(SaleItem si : firstSale.getItems()) {
            silm.addElement(si);
            result = result.add(si.getPrice());
        }
        assertEquals(silm.getTotalValue(),Utility.roundToNearest5Cents(result));
    }

    @Test
    public void Test_SelectedItemsListModel_TotalValue_Transaction2() {
        silm = new SelectedItemsListModel();
        BigDecimal result=new BigDecimal("0.00");
        for(SaleItem si : secondSale.getItems()) {
            silm.addElement(si);
            result = result.add(si.getPrice());
        }
        assertEquals(silm.getTotalValue(),Utility.roundToNearest5Cents(result));
    }

    @After
    public void cleanUp() {
        super.cleanUp();
        silm=null;
    }

}
