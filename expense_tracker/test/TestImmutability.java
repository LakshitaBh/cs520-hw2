// package test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.ExpenseTrackerModel;
import model.Transaction;


public class TestImmutability {
  
    private ExpenseTrackerModel model;
    private Transaction transaction;

    @Before
    public void setup() {
        model = new ExpenseTrackerModel();
    }

    @Test
    public void testImmutableTransactions() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        transaction = new Transaction(50.00, "food");
        model.addTransaction(transaction);
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Try to append to the list of transactions
        List<Transaction> transactions = model.getTransactions();
        transactions.add(new Transaction(100.00, "bills"));
        Transaction t = transactions.get(0);
        assertEquals(1, model.getTransactions().size());
        assertEquals(t, model.getTransactions().get(0));

        // Try to remove from the list of transactions
        transactions = model.getTransactions();
        t = transactions.get(0);
        transactions.remove(0);
        assertEquals(1, model.getTransactions().size());
        assertEquals(t, model.getTransactions().get(0));

    }
    
}