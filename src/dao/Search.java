package dao;

import model.Transaction;

import java.util.List;

public interface Search {
    List<Transaction> searchTransactionByTagFinance(int id);
    List<Transaction> searchTransactionByTagFinanceName(String key);



}
