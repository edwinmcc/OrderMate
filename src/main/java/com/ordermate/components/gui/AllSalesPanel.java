package com.ordermate.components.gui;

import com.ordermate.components.model.AllSalesTableModel;
import com.ordermate.components.renderer.AllSalesTableRenderer;
import javax.swing.*;
import java.awt.*;

/**
 * Created by edwin on 21/05/17.
 */
public class AllSalesPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private AllSalesTableModel tableModel;
    private AllSalesTableRenderer cellRenderer;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private Action closeAction;
    private JButton closeButton;
    private JLabel  totalLabel;

    public AllSalesPanel(AllSalesTableModel tableModel, Action closeAction) {
        this.tableModel=tableModel;
        this.closeAction = closeAction;
    }

    public void init() {

        cellRenderer = new AllSalesTableRenderer();

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setDragEnabled(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultRenderer(Object.class,cellRenderer);


        scrollPane = new JScrollPane(table);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        closeButton = new JButton();
        closeButton.setAction(closeAction);
        bottomPanel.add(closeButton);
        closeButton.setPreferredSize(new Dimension(200,closeButton.getPreferredSize().height));

        centerPanel = new JPanel();

        totalLabel = new JLabel();
        totalLabel.setHorizontalAlignment(JLabel.RIGHT);
        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD,22));

        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(scrollPane,BorderLayout.CENTER);
        centerPanel.add(totalLabel,BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(centerPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public void updateTotal() {
        totalLabel.setText("Total Sales : "+tableModel.getTotalSales().toString());
    }
}
