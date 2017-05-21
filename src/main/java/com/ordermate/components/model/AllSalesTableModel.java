package com.ordermate.components.model;

import com.ordermate.components.gui.AllSalesPanel;
import com.ordermate.data.Sale;
import com.ordermate.data.SaleItem;

import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;

/**
 * Created by edwin on 21/05/17.
 */
public class AllSalesTableModel extends DefaultTableModel {

    private SimpleDateFormat dateFormat=null;
    private SimpleDateFormat timeFormat=null;

    public AllSalesTableModel(String ...columnNames) {
        super(columnNames,0);
        dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
        timeFormat=new SimpleDateFormat("hh:mm:ss a");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void add(Sale sale) {
        int size=getRowCount()+1;
        String date=dateFormat.format(sale.getTransactionTime());
        String time=timeFormat.format(sale.getTransactionTime());
        long numberOfItems = sale.getItems().stream().map(SaleItem::getItemCount).reduce(0,Integer::sum);
        String saleValue= sale.getSaleValue().toString();
        String[] rowData = { String.valueOf(size), date, time, String.valueOf(numberOfItems),saleValue };
        addRow(rowData);
    }

}
