package com.ordermate.components.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by edwin on 21/05/17.
 */
public class AllSalesTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return label;
    }
}
