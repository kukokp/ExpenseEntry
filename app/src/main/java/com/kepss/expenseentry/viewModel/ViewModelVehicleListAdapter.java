package com.kepss.expenseentry.viewModel;

import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.kepss.expenseentry.ActivityVehicleFillPetrol;
import com.kepss.expenseentry.ActivityVehicleList;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;

public class ViewModelVehicleListAdapter extends BaseObservable {

    @Bindable
    public String strName, strNumber, strKm;
    ActivityVehicleList activityVehicleList;

    public ViewModelVehicleListAdapter(tbl_vehicle_hdr hdr, ActivityVehicleList activityVehicleList) {
        this.strName = hdr.getVehicleName();
        this.strNumber = hdr.getVehicleNumber();
        this.strKm = hdr.getCurrantKM();
        this.activityVehicleList = activityVehicleList;
    }

    public void vehicleOnClick() {
        activityVehicleList.startActivity(new Intent(activityVehicleList, ActivityVehicleFillPetrol.class));
    }
}
