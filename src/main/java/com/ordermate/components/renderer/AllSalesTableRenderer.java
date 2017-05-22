package com.ordermate.components.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by edwin on 21/05/17.
 */
public class AllSalesTableRenderer extends DefaultTableCellRenderer {

    private Color grayColor=Color.decode("#eeeeee");
    private Color bgColor;

    public AllSalesTableRenderer() {
        bgColor=getBackground();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setOpaque(true);
        if(column<4) {
            label.setHorizontalAlignment(JLabel.CENTER);
        }
        else {
            label.setHorizontalAlignment(JLabel.RIGHT);
        }

        if(row%2==0) {
            label.setBackground(bgColor);
        }
        else
        {
            label.setBackground(grayColor);
        }
        if(isSelected) {
            label.setBackground(table.getSelectionBackground());
        }
        return label;
    }
}
