package com.kepss.expenseentry.table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class tbl_expense {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String name;
    private String amt;
    private String expDate;
    private String expTime;
    private String IsInCome;

    public tbl_expense(String name, String amt, String expDate, String expTime, String isInCome) {
        this.name = name;
        this.amt = amt;
        this.expDate = expDate;
        this.expTime = expTime;
        IsInCome = isInCome;
    }

    public tbl_expense() {
    }

    public String getIsInCome() {
        return IsInCome;
    }

    public void setIsInCome(String isInCome) {
        IsInCome = isInCome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }
}
