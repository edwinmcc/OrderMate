package com.ordermate.utils;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by edwin on 20/05/17.
 */
public class Utility {

    private static BigDecimal bdDivide=new BigDecimal("0.05");
    private static BigDecimal bdCompare=new BigDecimal("0.02");



    public static ImageIcon newIcon(String imageName) {
        ImageIcon icon=null;
        URL url=Utility.class.getClass().getResource(imageName);
        if(url==null) {
            icon=new ImageIcon();
        }
        else {
            icon=new ImageIcon(url);
        }
        return icon;
    }

    public static BigDecimal roundToNearest5Cents(BigDecimal value){

        /*

        Original number - returned number

        1.00 - 1.00
        1.01 - 1.00
        1.02 - 1.00
        1.03 - 1.05
        1.04 - 1.05
        1.05 - 1.05
        1.06 - 1.05
        1.07 - 1.05
        1.08 - 1.10
        1.09 - 1.10

         */

        BigDecimal remainder=value.setScale(2,BigDecimal.ROUND_HALF_UP).remainder(bdDivide);
        if(remainder.compareTo(bdCompare)==1) {
            value=value.subtract(remainder).add(bdDivide);
        }
        else {
            value=value.subtract(remainder);
        }
        return value;
    }

}
