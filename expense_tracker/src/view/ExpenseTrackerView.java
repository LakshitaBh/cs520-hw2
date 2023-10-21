package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;


public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton amountFilterBtn;
  private JButton categoryFilterBtn;
  private JButton resetBtn;
  private JFormattedTextField amountField;
  private JFormattedTextField amountFilterField;
  private JTextField categoryField;
  private JTextField categoryFilterField;
  private DefaultTableModel model;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    amountFilterBtn = new JButton("Filter Amount");
    categoryFilterBtn = new JButton("Filter Category");
    resetBtn = new JButton("Reset");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    JLabel categoryLabel = new JLabel("Category:");

    JLabel amountFilterLabel = new JLabel("Amount");
    JLabel categoryFilterLabel = new JLabel("Category");

    NumberFormat format = NumberFormat.getNumberInstance();
    
    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);
    
    amountFilterField = new JFormattedTextField(format);
    amountFilterField.setColumns(10);
    
    categoryField = new JTextField(10);

    categoryFilterField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    JPanel filterPanel = new JPanel();
    filterPanel.add(amountFilterLabel);
    filterPanel.add(amountFilterField);
    filterPanel.add(amountFilterBtn);
    filterPanel.add(categoryFilterLabel);
    filterPanel.add(categoryFilterField);
    filterPanel.add(categoryFilterBtn);
    filterPanel.add(resetBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    // to stack buttonPanel and filterPanel one below the other we create a new panel for it
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
    southPanel.add(buttonPanel);
    southPanel.add(Box.createVerticalStrut(10)); // You can adjust the spacing as needed
    southPanel.add(filterPanel);
    add(southPanel, BorderLayout.SOUTH);

  
    // Set frame properties
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getAmountFilterBtn() {
    return amountFilterBtn;
  }
  public JButton getCategoryFilterBtn() {
    return categoryFilterBtn;
  }
  public JButton getResetBtn() {
    return resetBtn;
  }
  
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public double getAmountFilterField() {
    if(amountFilterField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountFilterField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public String getCategoryFilterField() {
    return categoryFilterField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
  public void highlightMatchingRows(List<Integer> rowIdxs) {
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
      @Override
      public Component getTableCellRendererComponent(
          JTable table, Object value, boolean isSelected,
          boolean hasFocus, int row, int column){
            Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            if(rowIdxs.contains(row)){
              c.setBackground(new Color(173,255,168));
            }
            else{
              c.setBackground(Color.WHITE);
            }
            return c;
          }
    });
    transactionsTable.updateUI();
  }
}
