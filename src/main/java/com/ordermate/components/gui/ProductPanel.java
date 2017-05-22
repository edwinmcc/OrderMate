package com.ordermate.components.gui;

import com.ordermate.components.listener.ItemSelectedListener;
import com.ordermate.data.Product;
import com.ordermate.data.SaleItem;
import com.ordermate.utils.Utility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by edwin on 20/05/17.
 */
public class ProductPanel extends JPanel implements MouseListener {

    private Product product;
    private JLabel  labelProductName;
    private JLabel  labelProductPrice;
    private JLabel  productImage;
    private ItemSelectedListener<SaleItem> itemSelectedListener;
    private Dimension preferredSize;
    private Border thickBorder=null;
    private Border noBorder=null;

    public ProductPanel(Product product) {
        this.product=product;
        init();
    }

    public void setItemSelectedListener(ItemSelectedListener<SaleItem> itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    private void logMessage(String msg) {
        System.out.println("ProductPanel : "+msg);
    }

    private ImageIcon getProductImage() {
        ImageIcon productImageIcon = Utility.newIcon(product.getImageName());
        return productImageIcon;
    }

    private void createGUIComponents() {
        labelProductName = new JLabel();
        labelProductPrice = new JLabel();
        productImage = new JLabel();
        thickBorder = BorderFactory.createLineBorder(Color.BLACK,3);
        noBorder = BorderFactory.createEmptyBorder();
    }

    private void init() {
        createGUIComponents();
        setLayout(new BorderLayout());
        labelProductName.setText(product.getName());
        setProductDetails(product);
        labelProductName.setFont(labelProductName.getFont().deriveFont(Font.PLAIN,18));
        labelProductPrice.setFont(labelProductPrice.getFont().deriveFont(Font.PLAIN,18));
        labelProductName.setHorizontalAlignment(JLabel.CENTER);
        labelProductPrice.setHorizontalAlignment(JLabel.CENTER);
        productImage.setIcon(getProductImage());
        add(productImage,BorderLayout.CENTER);
        add(labelProductName,BorderLayout.NORTH);
        add(labelProductPrice,BorderLayout.SOUTH);
        preferredSize=new Dimension(200,200);
        addMouseListener(this);
    }

    private void setProductDetails(Product product) {
        if(product.getPrice().equals(new BigDecimal("0.00"))) {
            labelProductName.setText("");
            labelProductPrice.setText("");
        }
        else {
            labelProductPrice.setText(product.getPrice().toString());
        }
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(itemSelectedListener==null) {
            return;
        }
        SaleItem selectedItem = new SaleItem(product);
        itemSelectedListener.itemSelected(selectedItem);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(thickBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(noBorder);
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

}
