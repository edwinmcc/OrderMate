package com.ordermate.components.gui;

import com.ordermate.components.listener.ItemChangeListener;
import com.ordermate.components.model.GroupsModel;
import com.ordermate.components.renderer.GroupsRenderer;
import com.ordermate.data.ProductGroup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by edwin on 19/05/17.
 */
public class GroupsPanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private JList       groupsList;
    private GroupsModel groupsModel=null;
    private GroupsRenderer groupsRenderer=null;
    private ItemChangeListener<ProductGroup> itemChangeListener=null;

    public GroupsPanel() {

    }

    public void init() {
        groupsModel = new GroupsModel();
        groupsModel.init();
        groupsList = new JList(groupsModel);
        groupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        groupsList.addListSelectionListener(this);
        groupsList.setFixedCellHeight(80);
        scrollPane=new JScrollPane(groupsList);
        groupsRenderer = new GroupsRenderer();
        groupsList.setCellRenderer(groupsRenderer);
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
    }

    public void setItemChangeListener(ItemChangeListener<ProductGroup> itemChangeListener) {
        this.itemChangeListener=itemChangeListener;
    }

    @Override
    public Dimension getPreferredSize() {
        //return super.getPreferredSize();
        return new Dimension(200,600);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            return;
        }
        if(itemChangeListener==null) {
            return;
        }
        int selectedIndex=groupsList.getSelectedIndex();
        if(selectedIndex==-1) {
            return;
        }
        ProductGroup selectedGroup=groupsModel.get(selectedIndex);
        itemChangeListener.itemChanged(selectedGroup);
    }
}
