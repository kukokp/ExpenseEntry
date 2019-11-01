package com.kepss.expenseentry.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kepss.expenseentry.R;
import com.kepss.expenseentry.databinding.ListExpenseReportBinding;
import com.kepss.expenseentry.table.tbl_expense;
import com.kepss.expenseentry.viewModel.ViewModelExpenseReport;

import java.util.ArrayList;
import java.util.List;

public class AdapterExpenseReport extends RecyclerView.Adapter<AdapterExpenseReport.Holder> {

    List<tbl_expense> list;
    List<tbl_expense> filterList;

    public AdapterExpenseReport() {
        this.list = new ArrayList<>();
        this.filterList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expense_report, parent, false);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expense_report,
                new FrameLayout(parent.getContext()), false);
        return new Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        tbl_expense dataModel = filterList.get(position);

        holder.setView(new ViewModelExpenseReport(dataModel));

    }

    @Override
    public int getItemCount() {
        if (filterList.size() == 0) {
            return 0;
        } else {
            return filterList.size();
        }
    }


    @Override
    public void onViewDetachedFromWindow(Holder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unBind();
    }


    @Override
    public void onViewAttachedToWindow(Holder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    public void setItem(List<tbl_expense> alItem) {
        if (alItem == null || alItem.isEmpty()) {
            this.list.clear();
            this.filterList.clear();
        } else {
            this.list = alItem;
            this.filterList = new ArrayList<>();
            this.filterList.addAll(alItem);
        }


        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ListExpenseReportBinding listExpenseReportBinding;


        public Holder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (listExpenseReportBinding == null) {
                listExpenseReportBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unBind() {
            if (listExpenseReportBinding != null) {
                listExpenseReportBinding.unbind();
            }
        }

        void setView(ViewModelExpenseReport view) {
            if (listExpenseReportBinding != null) {
                listExpenseReportBinding.setViewModel(view);
            }
        }
    }
}
