package com.kepss.expenseentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.kepss.expenseentry.databinding.ActivityVehicalListBinding;
import com.kepss.expenseentry.viewModel.ViewModelActivityAddVehicle;

public class ActivityVehicleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityVehicalListBinding activityVehicalListBinding= DataBindingUtil.setContentView(this,R.layout.activity_vehical_list);
        ViewModelActivityAddVehicle viewModelActivityAddVehicle = new ViewModelActivityAddVehicle(this);
        activityVehicalListBinding.setViewModel(viewModelActivityAddVehicle);
    }
}
