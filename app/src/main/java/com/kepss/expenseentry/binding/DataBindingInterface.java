package com.kepss.expenseentry.binding;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kepss.expenseentry.adapter.AdapterAutoCompleteTextView;
import com.kepss.expenseentry.adapter.AdapterExpenseReport;
import com.kepss.expenseentry.adapter.AdapterVehicleList;
import com.kepss.expenseentry.model.clsAutocompleteTextViewRes;
import com.kepss.expenseentry.table.tbl_expense;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;

import java.util.ArrayList;
import java.util.List;

public interface DataBindingInterface {

    @BindingAdapter({"app:rvVehicleAdapter", "app:alVehicleList"})
    void RVVehicle(RecyclerView recyclerView, AdapterVehicleList adapterVehicleList, List<tbl_vehicle_hdr> alItem);

    @BindingAdapter({"app:rvAdapter", "app:allist"})
    void RVExpenseReport(RecyclerView recyclerView, AdapterExpenseReport adapterExpenseReport, List<tbl_expense> alItem);

    @BindingAdapter({"app:atvAdapter", "app:alItem"})
    void setAutoCompleteTextView(AutoCompleteTextView autoCompleteTextView, AdapterAutoCompleteTextView adapterAutoCompleteTextView, ArrayList<clsAutocompleteTextViewRes> alItem);

    @BindingAdapter({"app:isFocus"})
    void setFocusEditText(EditText editText, boolean isFocus);

}
