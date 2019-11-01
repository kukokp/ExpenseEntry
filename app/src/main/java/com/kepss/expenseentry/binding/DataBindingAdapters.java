package com.kepss.expenseentry.binding;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kepss.expenseentry.R;
import com.kepss.expenseentry.adapter.AdapterAutoCompleteTextView;
import com.kepss.expenseentry.adapter.AdapterExpenseReport;
import com.kepss.expenseentry.adapter.AdapterVehicleList;
import com.kepss.expenseentry.model.clsAutocompleteTextViewRes;
import com.kepss.expenseentry.table.tbl_expense;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;

import java.util.ArrayList;
import java.util.List;

public class DataBindingAdapters implements DataBindingInterface {


    @Override
    public void RVVehicle(RecyclerView recyclerView, AdapterVehicleList adapterVehicleList, List<tbl_vehicle_hdr> alItem) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapterVehicleList);

        if (alItem != null) {
            adapterVehicleList.setItem(alItem);
        }
    }

    @Override
    public void RVExpenseReport(RecyclerView recyclerView, AdapterExpenseReport adapterExpenseReport, List<tbl_expense> alItem) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapterExpenseReport);
        if (alItem != null) {

            adapterExpenseReport.setItem(alItem);
        }
    }

    @Override
    public void setAutoCompleteTextView(AutoCompleteTextView autoCompleteTextView, AdapterAutoCompleteTextView adapterAutoCompleteTextView, ArrayList<clsAutocompleteTextViewRes> alItem) {

        if (alItem != null) {
            adapterAutoCompleteTextView = new AdapterAutoCompleteTextView(adapterAutoCompleteTextView.getContext(), R.layout.list_autocomplete_textview, alItem);
            autoCompleteTextView.setAdapter(adapterAutoCompleteTextView);
        }
    }

    @Override
    public void setFocusEditText(EditText editText, boolean isFocus) {
        if (isFocus) {
            editText.requestFocus();
        }
    }

}
