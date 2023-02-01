package view;

import controller.Controller;
import dao.*;
import model.TagFinance;
import model.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private static List<TagFinance> tagFinanceList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TagFinanceDAO tagFinanceDAO = new TagFinanceDAOImpl();
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        Search search = new TransactionDAOImpl();
        Controller controller = new Controller();

//        List<TagFinance> tagFinanceList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();


        int choice = 0;
        do {
            System.out.println("1.Insert tag finance");
            System.out.println("2.update tag finance");
            System.out.println("3.delete tag finance");
            System.out.println("4.show tag finance");
            System.out.println("5.Insert transaction");
            System.out.println("6.Update transaction");
            System.out.println("7.Delete transaction");
            System.out.println("8.Show transaction");
            System.out.println("9.Search");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    TagFinance tagFinance = controller.createTagFinance();
                    tagFinanceDAO.insertTagFinance(tagFinance);
                    break;
                }
                case 2: {
                    System.out.println("id?");
                    int id = sc.nextInt();
                    TagFinance tagFinance = controller.createTagFinance();
                    tagFinanceDAO.updateTagFinance(tagFinance, id);
                    break;
                }
                case 3: {
                    System.out.println("id?");
                    int id = sc.nextInt();
                    tagFinanceDAO.deleteTagFinance(id);
                    break;
                }
                case 4: {
                    tagFinanceList = tagFinanceDAO.getTagFinanceList();
                    showTagFinance(tagFinanceList);
                    break;
                }
                case 5: {
                    Transaction transaction = controller.createTransaction(tagFinanceList);
                    transactionDAO.insertTransaction(transaction);
                    break;

                }
                case 6: {
                    System.out.println("id?");
                    int id = sc.nextInt();
                    Transaction transaction = controller.createTransaction(tagFinanceList);
                    transactionDAO.updateTransaction(transaction, id);
                    break;
                }
                case 7: {
                    System.out.println("id?");
                    int id = sc.nextInt();
                    transactionDAO.deleteTransaction(id);
                    break;

                }
                case 8: {
                    List<Transaction> list = new ArrayList<>();
                    transactionList = transactionDAO.getTransactionList();
                    list = controller.updateTagFinanceForTransaction(transactionList, tagFinanceList);
                    showTransaction(list);
                    break;
                }
                case 9: {
                    showTagFinance(tagFinanceList);
                    System.out.println("choice id tag finance to search");
                    int id = sc.nextInt();
                    transactionList = search.searchTransactionByTagFinance(id);
                    showTransaction(transactionList);
                    break;

                }
                case 10: {
                    List<Transaction> list = new ArrayList<>();
                    List<Transaction> transactions = new ArrayList<>();
                    transactionList = transactionDAO.getTransactionList();
                    list = controller.updateTagFinanceForTransaction(transactionList, tagFinanceList);
                    String key = sc.next();
                    transactions = search.searchTransactionByTagFinanceName(key, list);
                    showTransaction(transactions);


                    break;
                }
            }
        } while (choice != 0);


    }

//    private static TagFinance createTagFinance() {
//        Scanner sc = new Scanner(System.in);
//        int id = 0;
//        System.out.println("name");
//        String name = sc.next();
//        System.out.println("description");
//        String description = sc.next();
//        TagFinance tagFinance = new TagFinance(id, name, description);
//        return tagFinance;
//    }

    private static void showTagFinance(List<TagFinance> tagFinanceList) {
        for (TagFinance tagFinance : tagFinanceList) {
            System.out.println(tagFinance);
        }
    }

//    private static Transaction createTransaction() {
//        Scanner sc = new Scanner(System.in);
//
//        int id = 0;
//        System.out.println("create At");
//        long millis=System.currentTimeMillis();
//        java.sql.Date cr= new Date(millis);
//
//        System.out.println("title");
//        String title = sc.next();
//        System.out.println("description");
//        String des = sc.next();
//        System.out.println("amount");
//        int amount = sc.nextInt();
//        for (int i = 0; i < tagFinanceList.size(); i++) {
//            System.out.println("id: " + tagFinanceList.get(i).getId() + " name" + tagFinanceList.get(i).getName());
//        }
//        System.out.println("choice tag id");
//        int tagID = sc.nextInt();
//        TagFinance tagFinance = new TagFinance();
//        for (int i = 0; i < tagFinanceList.size(); i++) {
//            if (tagFinanceList.get(i).getId() == tagID) {
//                tagFinance = tagFinanceList.get(i);
//            }
//        }
//        Transaction transaction = new Transaction(id,  cr, des, title, amount, tagFinance);
//        return transaction;
//
//
//    }

    private static void showTransaction(List<Transaction> transactionList) {
        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }
    }

//    private static List<Transaction> updateTagFinanceForTransaction(List<Transaction> list) {
//        for (Transaction transaction : list) {
//            for (int i = 0; i < tagFinanceList.size(); i++) {
//                if (transaction.getTagFinance().getId() == tagFinanceList.get(i).getId()) {
//                    transaction.setTagFinance(tagFinanceList.get(i));
//                }
//            }
//        }
//        return list;
//    }
}
