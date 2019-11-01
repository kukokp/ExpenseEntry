package com.kepss.expenseentry.binding;


public class AppDataBindingComponent implements androidx.databinding.DataBindingComponent {

    public DataBindingInterface getDataBindingInterface() {
        return new DataBindingAdapters();
    }
}
