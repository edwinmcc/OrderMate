package com.ordermate.components.renderer;

import com.ordermate.data.SaleItem;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by edwin on 20/05/17.
 */
public class SelectedItemRenderer extends JPanel implements ListCellRenderer<SaleItem> {

    private JLabel    itemName;
    private JLabel    itemCount;
    private JLabel    itemPrice;
    private Color     hoverColor;
    private Color     selectedColor;
    private Color     originalColor;

    public SelectedItemRenderer() {

    }

    public void init() {
        setLayout(new GridBagLayout());
        itemName = new JLabel();
        itemCount = new JLabel();
        itemPrice = new JLabel();
        itemPrice.setHorizontalAlignment(JLabel.RIGHT);
        hoverColor = Color.decode("0xffe1e6");
        selectedColor = Color.decode("0xe7e7e7");

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 3, 1, 1.0, .5, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(itemName, gbc);
        gbc = new GridBagConstraints(3, 0, 1, 2, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(itemPrice, gbc);
        gbc = new GridBagConstraints(0, 1, 3, 1, 1.0, 0.5, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(itemCount, gbc);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends SaleItem> list, SaleItem item, int index, boolean isSelected, boolean cellHasFocus) {
        itemName.setText(getProductName(item));
        itemCount.setText(getItemCount(item));
        itemPrice.setText(getItemPrice(item));

        if(isSelected) {
            setComponentsColor(selectedColor);
        }
        else {
            setComponentsColor(list.getBackground());
        }
        return this;
    }

    private void setComponentsColor(Color c) {
        setBackground(c);
        itemName.setBackground(c);
        itemCount.setBackground(c);
        itemPrice.setBackground(c);
    }

    private String getProductName(SaleItem item) {
        return item.getProduct().getName();
    }

    private String getItemCount(SaleItem item) {
        return new StringBuilder().append(item.getItemCount()).append(" X ").append(item.getProduct().getPrice().toString()).toString();
    }

    private String getItemPrice(SaleItem item) {
         return item.getProduct().getPrice().multiply(new BigDecimal(item.getItemCount())).setScale(2, RoundingMode.CEILING).toString();
    }


}
