package org.aplas.outincome.model;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String DateCash;
    private int Amount;
    private String Description;
    private String Status;
    private int getInCash;
    private int getOutCash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCash() {
        return DateCash;
    }

    public void setDateCash(String dateCash) {
        DateCash = dateCash;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getGetInCash() {
        return getInCash;
    }

    public void setGetInCash(int getInCash) {
        this.getInCash = getInCash;
    }

    public int getGetOutCash() {
        return getOutCash;
    }

    public void setGetOutCash(int getOutCash) {
        this.getOutCash = getOutCash;
    }
}
