package model;

import java.util.Date;

public class Transaction {
    private int id;
    private Date createdAt;
    private String title;
    private String description;
    private int amount;
    private TagFinance tagFinance;

    public Transaction() {
    }

    public Transaction(Date createdAt, String title, String description, int amount, TagFinance tagFinance) {
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.tagFinance = tagFinance;
    }

    public Transaction(int id, Date createdAt, String title, String description,
                       int amount, TagFinance tagFinance) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.tagFinance = tagFinance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TagFinance getTagFinance() {
        return tagFinance;
    }

    public void setTagFinance(TagFinance tagFinance) {
        this.tagFinance = tagFinance;
    }

//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "id=" + id +
//                ", createdAt='" + createdAt + '\'' +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", amount=" + amount +
//                ", tagFinanceID=" + tagFinance.getId() +
//                '}';
//    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", tagFinance=" + tagFinance +
                '}';
    }
}
