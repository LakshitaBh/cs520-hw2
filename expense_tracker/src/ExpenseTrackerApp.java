import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import java.util.List;
import java.util.ArrayList;


public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle amount filter button clicks
    view.getAmountFilterBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountFilterField();
      
      // Call controller to add transaction
      boolean added = controller.applyFilter("Amount", amount);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount entered");
        view.toFront();
      }
    });

    // Handle category filter button clicks
    view.getCategoryFilterBtn().addActionListener(e -> {
      // Get transaction data from view
      String category = view.getCategoryFilterField();
      
      // Call controller to add transaction
      boolean added = controller.applyFilter("Category", category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid category entered");
        view.toFront();
      }
    });

    // Handle reset button clicks
    view.getResetBtn().addActionListener(e -> { 
      List<Transaction> transactions = model.getTransactions();
      view.refreshTable(transactions);
      view.highlightMatchingRows(new ArrayList<Integer>());
    });

  }

}