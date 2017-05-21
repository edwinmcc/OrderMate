package com.ordermate.components.renderer;

import com.ordermate.data.ProductGroup;

import javax.swing.*;
import java.awt.*;

/**
 * Created by edwin on 19/05/17.
 */
public class GroupsRenderer extends DefaultListCellRenderer {

    private Font font=null;

    public GroupsRenderer() {
        this.font=new Font("Serif",Font.BOLD,24);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label=(JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        ProductGroup productGroup=(ProductGroup)value;
        label.setText(productGroup.getGroupName());
        label.setFont(font);
        return label;
    }
}
