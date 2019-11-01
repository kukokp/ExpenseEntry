package com.kepss.expenseentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.kepss.expenseentry.databinding.ActivityExpenseReportBinding;
import com.kepss.expenseentry.viewModel.ViewModelExpenseReport;

public class ActivityExpenseReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityExpenseReportBinding activityExpenseReportBinding = DataBindingUtil.setContentView(this, R.layout.activity_expense_report);
        ViewModelExpenseReport viewModelExpenseReport = new ViewModelExpenseReport(this);
        activityExpenseReportBinding.setViewModel(viewModelExpenseReport);
    }
}
