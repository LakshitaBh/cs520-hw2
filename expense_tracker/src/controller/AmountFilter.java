package controller;

import java.util.List;
import model.Transaction;
import java.util.ArrayList;


public class AmountFilter implements TransactionFilter {
    private double amountToFilter;

    public AmountFilter(double amount) {
        amountToFilter = amount;
    }

    @Override
    public List<Integer> filter(List<Transaction> transactions) {
        List<Integer> filteredTransactions = new ArrayList<>();

        // for (Transaction transaction : transactions) {
        for (int i=0; i<transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            double amount = transaction.getAmount();

            if (amount == amountToFilter) {
                filteredTransactions.add(i);
            }
        }

        return filteredTransactions;
    }
}