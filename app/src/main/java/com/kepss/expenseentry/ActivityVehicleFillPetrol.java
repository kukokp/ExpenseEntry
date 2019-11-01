package com.kepss.expenseentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.kepss.expenseentry.databinding.ActivityVehicleFillPetrolBinding;
import com.kepss.expenseentry.viewModel.ViewModelFillPetrol;

public class ActivityVehicleFillPetrol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityVehicleFillPetrolBinding activityVehicleFillPetrolBinding = DataBindingUtil.setContentView(this,R.layout.activity_vehicle_fill_petrol);
        ViewModelFillPetrol viewModelFillPetrol = new ViewModelFillPetrol(this);

        activityVehicleFillPetrolBinding.setViewModel(viewModelFillPetrol);

    }
}
