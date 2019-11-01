package com.kepss.expenseentry.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.kepss.expenseentry.ActivityVehicleFillPetrol;

public class ViewModelFillPetrol extends BaseObservable {

    @Bindable
    public String strCurrantKm, strLastKm, strCurrantRate, strCurrantFillAmount;

    ActivityVehicleFillPetrol activityVehicleFillPetrol;

    public ViewModelFillPetrol(ActivityVehicleFillPetrol activityVehicleFillPetrol) {
        this.activityVehicleFillPetrol = activityVehicleFillPetrol;
    }


}
