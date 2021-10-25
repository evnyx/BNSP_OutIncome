package org.aplas.outincome.model;

public class DataList {
    private int id;
    private String date;
    private int amount;
    private String description;
    private String status;

    public DataList(int id, String date, int amount, String description, String status) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
