package com.kepss.expenseentry.table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class tbl_config {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String config_name;
    private String config_value;

    public tbl_config(String config_name, String config_value) {
        this.config_name = config_name;
        this.config_value = config_value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfig_name() {
        return config_name;
    }

    public void setConfig_name(String config_name) {
        this.config_name = config_name;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }
}
