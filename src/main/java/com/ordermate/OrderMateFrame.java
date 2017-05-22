package com.ordermate;

import com.ordermate.components.gui.AllSalesPanel;
import com.ordermate.components.gui.GroupsPanel;
import com.ordermate.components.gui.ProductsPanel;
import com.ordermate.components.gui.SelectedItemsPanel;
import com.ordermate.components.model.AllSalesTableModel;
import com.ordermate.components.model.SelectedItemsListModel;
import com.ordermate.data.Sale;
import com.ordermate.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class OrderMateFrame extends JFrame {

    // GroupsPanel shows the Groups like "Burger, Chips & Drinks" on the left side in Main Window.
    private GroupsPanel groupPanel;

    // Products Panel shows the list of items belonging to a group
    private ProductsPanel itemsPanel;

    // Every item selected for a sale is listed in this Panel.
    private SelectedItemsPanel selectedItemsPanel;

    // AllSalesPanel shows the Tabular information of all the sales captured.
    private AllSalesPanel allSalesPanel;

    private SelectedItemsListModel selectedItemModel;
    private SaveAction saveAction;
    private java.util.List<Sale> allSales;
    private AllSalesTableModel allSalesTableModel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Action closeAllDayPanelAction = new CloseAllDaySalesAction();
    private JLabel topLabel=null;

    private JMenuBar menuBar=null;
    private JMenu menu = null;

    public OrderMateFrame() {

    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saveAction = new SaveAction();
        allSales = new ArrayList<>();
        addMenu();
        addGUIComponents();
    }

    private void createTopPanel() {
        topLabel=new JLabel();
        topLabel.setText("Joey’s Hamburger Barn");
        topLabel.setFont(topLabel.getFont().deriveFont(Font.PLAIN,30));
        topLabel.setPreferredSize(new Dimension(topLabel.getPreferredSize().width,100));
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setOpaque(true);
        topLabel.setBackground(Color.decode("#e0e0e0"));
    }

    private void createProductsPanel() {
        itemsPanel = new ProductsPanel();
        itemsPanel.init();
    }

    private void createGroupsPanel() {
        groupPanel = new GroupsPanel();
        groupPanel.init();
    }

    private void createAllSalesPanel() {
        String[] allSalesTableColumnNames = new String[] { "S. No. ", "Date", "Time", "Number Of Items", "Amount"};
        allSalesTableModel = new AllSalesTableModel(allSalesTableColumnNames);
        allSalesPanel = new AllSalesPanel(allSalesTableModel,closeAllDayPanelAction);
        allSalesPanel.init();
    }

    private void createCardPanel() {
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add("itemsPanel",itemsPanel);
        cardPanel.add("allSalesPanel",allSalesPanel);
    }

    private void createSelectedItemsPanel() {
        selectedItemModel = new SelectedItemsListModel();
        selectedItemsPanel = new SelectedItemsPanel();
        selectedItemsPanel.init(selectedItemModel);
        selectedItemsPanel.setSaveAction(saveAction);
    }

    private void addMenu() {
        menuBar  = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem allDaySalesItem = new JMenuItem("Total Sales Report");
        allDaySalesItem.setMnemonic(KeyEvent.VK_R);
        allDaySalesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
        allDaySalesItem.setAction(new AllDaySalesAction());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.ALT_MASK));
        exitMenuItem.setAction(new ExitAction());
        menu.add(allDaySalesItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void createGUIComponents() {
        createTopPanel();
        createProductsPanel();
        createGroupsPanel();
        createAllSalesPanel();
        createCardPanel();
        createSelectedItemsPanel();
    }

    private void addGUIComponents() {
        createGUIComponents();
        itemsPanel.setItemSelectedListener(selectedItemsPanel);
        groupPanel.setItemChangeListener(itemsPanel);
        add(topLabel,BorderLayout.NORTH);
        add(groupPanel, BorderLayout.WEST);
        add(cardPanel,BorderLayout.CENTER);
        add(new JScrollPane(selectedItemsPanel),BorderLayout.EAST);
        showProductPanel();
    }

    private void saveAndResetTransaction() {
        if(selectedItemModel.size()==0) {
            return;
        }
        Sale newSale=new Sale(selectedItemModel.getSaleItemsList(),selectedItemModel.getTotalValue());
        allSales.add(newSale);
        allSalesTableModel.add(newSale);
        allSalesPanel.updateTotal();
        selectedItemModel.clear();
        selectedItemsPanel.clearSales();
    }

    private void showAllDaySalesPanel() {
        cardLayout.show(cardPanel,"allSalesPanel");
    }

    private void showProductPanel() {
        cardLayout.show(cardPanel,"itemsPanel");
    }

    private void exitApplication() {
        System.exit(0);
    }


    class SaveAction extends  AbstractAction {
        public SaveAction() {
            super("Save & Next", Utility.newIcon("/images/save.png"));
            putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_S));
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
        }
        public void actionPerformed(ActionEvent ae) {
            saveAndResetTransaction();
        }
    }

    class AllDaySalesAction extends AbstractAction {
        public AllDaySalesAction() {
            super("Show All Sales", Utility.newIcon("/images/dummy.png"));
            putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_R));
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
        }
        public void actionPerformed(ActionEvent ae) {
            showAllDaySalesPanel();
        }
    }

    class CloseAllDaySalesAction extends AbstractAction {
        public CloseAllDaySalesAction() {
            super("Close", Utility.newIcon("/images/dummy.png"));
            putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_C));
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.ALT_MASK));
        }
        public void actionPerformed(ActionEvent ae) {
            showProductPanel();
        }
    }

    class ExitAction extends AbstractAction {
        public ExitAction() {
            super("Exit", Utility.newIcon("/images/dummy.png"));
            putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_X));
            putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.ALT_MASK));
        }
        public void actionPerformed(ActionEvent ae) {
            exitApplication();
        }
    }

}