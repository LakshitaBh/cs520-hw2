package controller;

import view.ExpenseTrackerView;

import java.util.List;
import java.util.ArrayList;



import model.ExpenseTrackerModel;
import model.Transaction;

import controller.TransactionFilter;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  public boolean applyFilter(String filterType, double filterValue) {
    // List<Integer> rowIdxs = new ArrayList<>();
    // rowIdxs.add(1);
    // view.highlightMatchingRows(rowIdxs);

    List<Transaction> transactions = model.getTransactions();
    AmountFilter filter  = null;
    if ("Amount".equals(filterType)) {
        double amount = filterValue;
        if (!InputValidation.isValidAmount(amount)) {
            return false;
        }
        filter = new AmountFilter(filterValue);
    }

    if (filter != null) {
        List<Integer> filteredTransactions = filter.filter(transactions);
        view.highlightMatchingRows(filteredTransactions);
    }

    return true;
}

public boolean applyFilter(String filterType, String filterValue) {
    List<Transaction> transactions = model.getTransactions();
    CategoryFilter filter  = null;

    if ("Category".equals(filterType)) {
        if (!InputValidation.isValidCategory(filterValue)) {
            return false;
        }
        filter = new CategoryFilter(filterValue);
    }

    if (filter != null) {
        List<Integer> filteredTransactions = filter.filter(transactions);
        view.highlightMatchingRows(filteredTransactions);
    }

    return true;
}


}