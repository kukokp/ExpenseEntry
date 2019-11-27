package com.kepss.expenseentry.ui.home;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.kepss.expenseentry.BR;

public class HomeViewModel extends BaseObservable {

    @Bindable
    public String strText;

    public HomeViewModel() {
        strText = "This is home ktrunl";
        notifyPropertyChanged(com.kepss.expenseentry.BR.strText);
    }

}