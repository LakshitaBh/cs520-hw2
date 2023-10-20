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
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            double amount = transaction.getAmount();

            if (amount == amountToFilter) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}