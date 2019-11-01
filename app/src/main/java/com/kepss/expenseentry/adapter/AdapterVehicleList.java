package com.kepss.expenseentry.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kepss.expenseentry.ActivityVehicleList;
import com.kepss.expenseentry.R;
import com.kepss.expenseentry.databinding.ListExpenseReportBinding;
import com.kepss.expenseentry.databinding.ListVehicleBinding;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;
import com.kepss.expenseentry.viewModel.ViewModelVehicleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterVehicleList extends RecyclerView.Adapter<AdapterVehicleList.Holder> {

    List<tbl_vehicle_hdr> list;
    List<tbl_vehicle_hdr> filterList;
    ActivityVehicleList activityVehicleList;

    public AdapterVehicleList(ActivityVehicleList activityVehicleList) {
        this.list = new ArrayList<>();
        this.filterList = new ArrayList<>();
        this.activityVehicleList = activityVehicleList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expense_report, parent, false);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vehicle,
                new FrameLayout(parent.getContext()), false);
        return new Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        tbl_vehicle_hdr dataModel = filterList.get(position);

        holder.setView(new ViewModelVehicleListAdapter(dataModel,activityVehicleList));

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

    public void setItem(List<tbl_vehicle_hdr> alItem) {
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

        ListVehicleBinding listVehicleBinding;


        public Holder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (listVehicleBinding == null) {
                listVehicleBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unBind() {
            if (listVehicleBinding != null) {
                listVehicleBinding.unbind();
            }
        }

        void setView(ViewModelVehicleListAdapter view) {
            if (listVehicleBinding != null) {
                listVehicleBinding.setViewModel(view);
            }
        }
    }
}
