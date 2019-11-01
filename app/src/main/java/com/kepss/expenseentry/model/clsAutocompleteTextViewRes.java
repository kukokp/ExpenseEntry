package com.kepss.expenseentry.model;

public class clsAutocompleteTextViewRes {
    public String name;
    public String value;
    public Object obj;

    public clsAutocompleteTextViewRes(String name, String value, Object obj) {
        this.name = name;
        this.value = value;
        this.obj = obj;
    }

    public clsAutocompleteTextViewRes(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public clsAutocompleteTextViewRes() {
    }
}
