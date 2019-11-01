package com.kepss.expenseentry.globle;

import android.app.Application;

import androidx.databinding.DataBindingUtil;

import com.kepss.expenseentry.binding.AppDataBindingComponent;

public class MainClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());

    }
}
