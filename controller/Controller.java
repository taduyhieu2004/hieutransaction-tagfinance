package controller;

import model.TagFinance;
import model.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public Transaction createTransaction(List<TagFinance> tagFinanceList) {
        Scanner sc = new Scanner(System.in);

        int id = 0;
        System.out.println("create At");
        long millis=System.currentTimeMillis();
        java.sql.Date cr=  new Date(millis);
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

    public List<Transaction> updateTagFinanceForTransaction(List<Transaction> list, List<TagFinance> tagFinanceList) {
        for (Transaction transaction : list) {
            for (int i = 0; i < tagFinanceList.size(); i++) {
                if (transaction.getTagFinance().getId() == tagFinanceList.get(i).getId()) {
                    transaction.setTagFinance(tagFinanceList.get(i));
                }
            }
        }
        return list;
    }

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
