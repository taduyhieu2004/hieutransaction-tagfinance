package view;

import controller.Controller;
import dao.*;
import dao.impl.TagFinanceDAOImpl;
import dao.impl.TransactionDAOImpl;
import model.TagFinance;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private static List<TagFinance> tagFinanceList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Controller controller = new Controller();

        List<Transaction> transactionList = new ArrayList<>();


        int choice = 0;
        do {
            showMenu();

            choice = sc.nextInt();
            switch (choice) {
                case 1: {

                    controller.insertTagFinance();
                    break;
                }
                case 2: {

                    controller.updateTagFinance();
                    break;
                }
                case 3: {

                    controller.deleteTagFinance();
                    break;
                }
                case 4: {

                    tagFinanceList = controller.getTagFinanceList();
                    showTagFinance(tagFinanceList);
                    break;
                }
                case 5: {

                    controller.insertTransaction(tagFinanceList);
                    break;

                }
                case 6: {

                    controller.updateTransaction(tagFinanceList);
                    break;
                }
                case 7: {

                    controller.deleteTransaction();
                    break;

                }
                case 8: {
                    transactionList = controller.transactionList();
                    showTransaction(transactionList);
                    break;
                }
//                case 9: {
//                    showTagFinance(tagFinanceList);
//                    transactionList = controller.searchTransactionList(tagFinanceList);
//                    showTransaction(transactionList);
//                    break;

//                }
                case 10: {
                    List<Transaction> transactions = controller.searchTransactionListByName();
                    showTransaction(transactions);


                    break;
                }
            }
        } while (choice != 0);


    }


    private static void showTagFinance(List<TagFinance> tagFinanceList) {
        for (TagFinance tagFinance : tagFinanceList) {
            System.out.println(tagFinance);
        }
    }


    private static void showTransaction(List<Transaction> transactionList) {
        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }
    }


    private static void showMenu() {
        System.out.println("1.Insert tag finance");
        System.out.println("2.update tag finance");
        System.out.println("3.delete tag finance");
        System.out.println("4.show tag finance");
        System.out.println("5.Insert transaction");
        System.out.println("6.Update transaction");
        System.out.println("7.Delete transaction");
        System.out.println("8.Show transaction");
        System.out.println("9.Search");
        System.out.println("10.Search by name");


    }
}
