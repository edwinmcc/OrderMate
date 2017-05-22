package com.ordermate;

import com.ordermate.utils.Utility;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class TestUtilityDecimalRounding
{
    @Test
    public void Test_Utility_RoundingToNearest5CentsLowerBound() {
        BigDecimal payment=new BigDecimal("5.12");
        BigDecimal expectedValue = new BigDecimal("5.10");
        assertEquals(expectedValue, Utility.roundToNearest5Cents(payment));
    }

    @Test
    public void Test_Utility_RoundingToNearest5CentsUpperBound() {
        BigDecimal payment=new BigDecimal("5.14");
        BigDecimal expectedValue = new BigDecimal("5.15");
        assertEquals(expectedValue, Utility.roundToNearest5Cents(payment));
    }

    @Test
    public void Test_Utility_RoundingToNearest5CentsUnAlteredEnding5() {
        BigDecimal payment=new BigDecimal("5.15");
        BigDecimal expectedValue = new BigDecimal("5.15");
        assertEquals(expectedValue, Utility.roundToNearest5Cents(payment));
    }

    @Test
    public void Test_Utility_RoundingToNearest5CentsUnAlteredEnding0() {
        BigDecimal payment=new BigDecimal("5.50");
        BigDecimal expectedValue = new BigDecimal("5.50");
        assertEquals(expectedValue, Utility.roundToNearest5Cents(payment));
    }

}
