package com.kepss.expenseentry.viewModel;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.kepss.expenseentry.ActivityVehicleList;
import com.kepss.expenseentry.BR;
import com.kepss.expenseentry.adapter.AdapterVehicleList;
import com.kepss.expenseentry.database.roomDatabase;
import com.kepss.expenseentry.globle.Config;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewModelActivityAddVehicle extends BaseObservable {

    @Bindable
    public String strName, strNumber, strStartKM;

    @Bindable
    public boolean isVisible = false;

    @Bindable
    public AdapterVehicleList adapterVehicleList;

    @Bindable
    public List<tbl_vehicle_hdr> alVehicle;

    public roomDatabase database;
    ActivityVehicleList activityVehicleList;

    Calendar calendar = Calendar.getInstance();

    SimpleDateFormat sdfDateTime = new SimpleDateFormat(Config.DATE_TIME_FORMAT, Locale.ENGLISH);

    public ViewModelActivityAddVehicle(ActivityVehicleList activityVehicalList) {
        database = roomDatabase.getContext(activityVehicalList);
        this.activityVehicleList = activityVehicalList;
        adapterVehicleList = new AdapterVehicleList(activityVehicleList);
        alVehicle = new ArrayList<>();
        notifyPropertyChanged(BR.adapterVehicleList);
        notifyPropertyChanged(BR.alVehicle);
        init();
    }

    private void init() {
        long count = Long.parseLong(database.vehicleDao().countOfVehicle());
        if (count > 0) {
            isVisible = true;
            alVehicle = database.vehicleDao().selectData();
            notifyPropertyChanged(BR.alVehicle);
        } else {
            isVisible = false;
        }
        notifyPropertyChanged(BR.isVisible);
    }

    public void saveData() {
        if (TextUtils.isEmpty(strName)) {
            Toast.makeText(activityVehicleList, "Please enter Vehicle Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strNumber)) {
            Toast.makeText(activityVehicleList, "Please enter Vehicle Number", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateTime = sdfDateTime.format(calendar.getTime());

        tbl_vehicle_hdr hdr = new tbl_vehicle_hdr(strName, strNumber, dateTime, strStartKM, strStartKM);

        long id = database.vehicleDao().saveData(hdr);
        if (id > 0) {

            Toast.makeText(activityVehicleList, "Data Save Successfully " + id, Toast.LENGTH_SHORT).show();
            init();
        } else {

            Toast.makeText(activityVehicleList, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelData() {
        isVisible = true;
        notifyPropertyChanged(BR.isVisible);
    }

    public void onFABClick() {
        isVisible = false;
        notifyPropertyChanged(BR.isVisible);
    }
}
