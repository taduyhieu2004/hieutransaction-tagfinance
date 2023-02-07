package controller;

import dao.Search;
import dao.TagFinanceDAO;
import dao.TransactionDAO;
import dao.impl.TagFinanceDAOImpl;
import dao.impl.TransactionDAOImpl;
import model.TagFinance;
import model.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private TagFinanceDAO tagFinanceDAO = new TagFinanceDAOImpl();
    private TransactionDAO transactionDAO = new TransactionDAOImpl();
    private Search search = new TransactionDAOImpl();
    private static Scanner sc = new Scanner(System.in);

    public void insertTagFinance() {
        TagFinance tagFinance = createTagFinance();
        tagFinanceDAO.insertTagFinance(tagFinance);
    }

    public void updateTagFinance() {
        System.out.println("id");
        int id = sc.nextInt();
        TagFinance tagFinance = createTagFinance();
        tagFinanceDAO.updateTagFinance(tagFinance, id);
    }

    public void deleteTagFinance() {
        System.out.println("id");
        int id = sc.nextInt();
        tagFinanceDAO.deleteTagFinance(id);
    }

    public List<TagFinance> getTagFinanceList() {
        List<TagFinance> list = new ArrayList<>();
        list = tagFinanceDAO.getTagFinanceList();
        return list;
    }

    public void insertTransaction(List<TagFinance> tagFinanceList) {
        Transaction transaction = createTransaction(tagFinanceList);
        transactionDAO.insertTransaction(transaction);
    }

    public void updateTransaction(List<TagFinance> tagFinanceList) {
        System.out.println("id");
        int id = sc.nextInt();
        Transaction transaction = createTransaction(tagFinanceList);
        transactionDAO.updateTransaction(transaction, id);

    }

    public void deleteTransaction() {
        System.out.println("id");
        int id = sc.nextInt();
        transactionDAO.deleteTransaction(id);
    }

    public List<Transaction> transactionList() {

        List<Transaction> transactionList = new ArrayList<>();
        transactionList = transactionDAO.getTransactionList();

        return transactionList;
    }

//    public List<Transaction> searchTransactionList(List<TagFinance> tagFinanceList) {
//        List<Transaction> list = new ArrayList<>();
//        List<Transaction> transactionList = new ArrayList<>();
//        System.out.println("choice id tag finance to search");
//        int id = sc.nextInt();
//        list = search.searchTransactionByTagFinance(id);
//        transactionList = updateTagFinanceForTransaction(list, tagFinanceList);
//        return transactionList;
//
//    }

    public List<Transaction> searchTransactionListByName() {
        List<Transaction> transactions = new ArrayList<>();
        String  key=sc.next();
        transactions=search.searchTransactionByTagFinanceName(key);

        return transactions;
    }

    public Transaction createTransaction(List<TagFinance> tagFinanceList) {
        Scanner sc = new Scanner(System.in);

        int id = 0;
        System.out.println("create At");
        long millis = System.currentTimeMillis();
        java.sql.Date cr = new Date(millis);
        System.out.println("title");
        String title = sc.next();
        System.out.println("description");
        String des = sc.next();
        System.out.println("amount");
        int amount = sc.nextInt();
        for (int i = 0; i < tagFinanceList.size(); i++) {
            System.out.println("id: " + tagFinanceList.get(i).getId() + " name" + tagFinanceList.get(i).getName());
        }
        System.out.println("choice tag id");
        int tagID = sc.nextInt();
        TagFinance tagFinance = new TagFinance();
        for (int i = 0; i < tagFinanceList.size(); i++) {
            if (tagFinanceList.get(i).getId() == tagID) {
                tagFinance = tagFinanceList.get(i);
            }
        }
        Transaction transaction = new Transaction(cr, des, title, amount, tagFinance);
        return transaction;


    }

//    public List<Transaction> updateTagFinanceForTransaction(List<Transaction> list, List<TagFinance> tagFinanceList) {
//        for (Transaction transaction : list) {
//            for (int i = 0; i < tagFinanceList.size(); i++) {
//                if (transaction.getTagFinance().getId() == tagFinanceList.get(i).getId()) {
//                    transaction.setTagFinance(tagFinanceList.get(i));
//                }
//            }
//        }
//        return list;
//    }

    public TagFinance createTagFinance() {
        Scanner sc = new Scanner(System.in);
        int id = 0;
        System.out.println("name");
        String name = sc.next();
        System.out.println("description");
        String description = sc.next();
        TagFinance tagFinance = new TagFinance(id, name, description);
        return tagFinance;
    }


}
