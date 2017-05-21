package com.ordermate;

import javax.swing.*;

/**
 * Created by edwin on 21/05/17.
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
