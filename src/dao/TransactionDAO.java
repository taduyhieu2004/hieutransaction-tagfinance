package dao;

import model.Transaction;

import java.util.List;

public interface TransactionDAO {
    void insertTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction,int id);
    void deleteTransaction(int id);
    List<Transaction>getTransactionList();
//    void insertTransactiontest(Transaction transaction);

}
