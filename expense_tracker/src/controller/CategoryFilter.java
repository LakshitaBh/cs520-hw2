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
    public List<Integer> filter(List<Transaction> transactions) {
        List<Integer> filteredTransactions = new ArrayList<>();

        // for (Transaction transaction : transactions) {
        for (int i=0; i<transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getCategory().equalsIgnoreCase(categoryToFilter)) {
                filteredTransactions.add(i);
            }
        }

        return filteredTransactions;
    }
}