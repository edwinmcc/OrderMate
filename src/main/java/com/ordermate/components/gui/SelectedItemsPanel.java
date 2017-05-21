package com.ordermate.components.gui;

import com.ordermate.components.listener.ItemSelectedListener;
import com.ordermate.components.model.SelectedItemsListModel;
import com.ordermate.components.renderer.SelectedItemRenderer;
import com.ordermate.data.SaleItem;
import com.ordermate.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by edwin on 20/05/17.
 */

public class SelectedItemsPanel extends JPanel implements ItemSelectedListener<SaleItem> {

    private Dimension preferredSize=new Dimension(400,800);
    private JList itemsList=null;
    private SelectedItemsListModel itemsModel;
    private SelectedItemRenderer cellRenderer = null;

    private JPanel topPanel;
    private JPanel bottomPanel;

    private JButton deleteButton;
    private JButton minusButton;
    private JButton plusButton;
    private JButton saveButton;
    private JLabel  labelTotalPrice;
    private JPanel  totalPricePanel;
    private JLabel  labelTotal;

    private Action saveAction;


    public SelectedItemsPanel() {

    }

    public void setSaveAction(Action saveAction) {
        this.saveAction=saveAction;
        saveButton.setAction(saveAction);
    }


    public void init(SelectedItemsListModel itemsModel) {
        this.itemsModel= itemsModel;
        cellRenderer = new SelectedItemRenderer();
        cellRenderer.init();
        itemsList = new JList(itemsModel);
        itemsList.setCellRenderer(cellRenderer);
        itemsList.setFixedCellHeight(60);
        setLayout(new BorderLayout());


        deleteButton = new JButton();
        minusButton  = new JButton();
        plusButton   = new JButton();
        saveButton   = new JButton();
        labelTotalPrice = new JLabel("0.00");
        labelTotal = new JLabel("Total");
        labelTotalPrice.setHorizontalAlignment(JLabel.RIGHT);
        labelTotalPrice.setFont(labelTotalPrice.getFont().deriveFont(Font.BOLD,24));
        labelTotal.setFont(labelTotalPrice.getFont().deriveFont(Font.BOLD,24));

        topPanel = new JPanel();
        bottomPanel = new JPanel();
        totalPricePanel = new JPanel();

        topPanel.setLayout(new GridLayout(1,3,10,10));
        bottomPanel.setLayout(new GridLayout(2,1,0,10));

        topPanel.add(deleteButton);
        topPanel.add(minusButton);
        topPanel.add(plusButton);

        totalPricePanel.setLayout(new BorderLayout());
        totalPricePanel.add(labelTotal,BorderLayout.WEST);
        totalPricePanel.add(labelTotalPrice,BorderLayout.CENTER);

        bottomPanel.add(totalPricePanel);
        bottomPanel.add(saveButton);



        deleteButton.setAction(new DeleteAction("Delete",Utility.newIcon("/images/delete.png")));
        minusButton.setAction(new MinusAction("Take",Utility.newIcon("/images/remove.png")));
        plusButton.setAction(new AddAction("Add",Utility.newIcon("/images/add.png")));
        saveButton.setAction(saveAction);

        add(new JScrollPane(itemsList));
        add(topPanel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.SOUTH);
        clearSales();
    }

    private void refreshGUI() {
        revalidate();
        repaint();
        itemsList.revalidate();
        itemsList.repaint();
    }

    private void logMessage(String msg) {
        System.out.println("SelectedItemPanel : "+msg);
    }

    @Override
    public void itemSelected(SaleItem selectedItem) {
        int itemIndex;
        if(itemsModel.contains(selectedItem)) {
            itemIndex=itemsModel.indexOf(selectedItem);
            SaleItem item=itemsModel.get(itemIndex);
            item.setItemCount(item.getItemCount() + 1);
            itemsModel.contentsChanged(itemIndex);
        }
        else {
            itemIndex = itemsModel.getSize();
            itemsModel.addElement(selectedItem);
        }
        itemsList.setSelectedIndex(itemIndex);
        updateTotal();
        refreshGUI();
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    private void updateTotal() {
        labelTotalPrice.setText(String.format("%s $",itemsModel.getTotalValue().toString()));
    }

    public void clearSales() {
        labelTotalPrice.setText("0.00 $");
    }

    private void deleteSelectedItem() {
        int selectedIndex = itemsList.getSelectedIndex();
        if(selectedIndex==-1) {
            return;
        }
        itemsModel.remove(selectedIndex);
        updateTotal();
    }

    private void reduceItemCount() {
        int selectedIndex = itemsList.getSelectedIndex();
        if(selectedIndex==-1) {
            return;
        }
        SaleItem item=itemsModel.get(selectedIndex);
        if(item.getItemCount()==1) {
            deleteSelectedItem();
        }
        else {
            item.setItemCount(item.getItemCount() - 1);
        }
        itemsModel.contentsChanged(selectedIndex);
        updateTotal();
    }

    private void addItemCount() {
        int selectedIndex = itemsList.getSelectedIndex();
        if(selectedIndex==-1) {
            return;
        }
        SaleItem item=itemsModel.get(selectedIndex);
        item.setItemCount(item.getItemCount() + 1);
        itemsModel.contentsChanged(selectedIndex);
        updateTotal();
    }

    class DeleteAction extends AbstractAction {

        public DeleteAction(String text,Icon icon) {
            super(text,icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteSelectedItem();
        }
    }

    class MinusAction extends AbstractAction {

        public MinusAction(String text,Icon icon) {
            super(text,icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            reduceItemCount();
        }
    }

    class AddAction extends AbstractAction {

        public AddAction(String text,Icon icon) {
            super(text,icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addItemCount();
        }
    }

}
