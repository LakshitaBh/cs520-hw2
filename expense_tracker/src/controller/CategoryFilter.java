package controller;

import java.util.List;
import java.util.ArrayList;
import model.Transaction;

public class CategoryFilter implements TransactionFilter {
    private String categoryToFilter;

    public CategoryFilter(String categoryToFilter) {
        this.categoryToFilter = categoryToFilter;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(categoryToFilter)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}