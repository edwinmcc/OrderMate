package com.ordermate;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by edwin on 21/05/17.
 */

/*
 * This class is used to invoke the main GUI class.
 */

public class OrderMateApplication {
    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OrderMateFrame om = new OrderMateFrame();
                om.init();
                om.setVisible(true);
            }
        });
    }
}
