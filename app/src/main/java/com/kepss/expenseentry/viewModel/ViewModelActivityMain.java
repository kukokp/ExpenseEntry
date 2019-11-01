package com.kepss.expenseentry.viewModel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.kepss.expenseentry.ActivityExpenseReport;
import com.kepss.expenseentry.ActivityVehicleList;
import com.kepss.expenseentry.BR;
import com.kepss.expenseentry.MainActivity;
import com.kepss.expenseentry.R;
import com.kepss.expenseentry.adapter.AdapterAutoCompleteTextView;
import com.kepss.expenseentry.database.roomDatabase;
import com.kepss.expenseentry.globle.Config;
import com.kepss.expenseentry.globle.PubFun;
import com.kepss.expenseentry.model.clsAutocompleteTextViewRes;
import com.kepss.expenseentry.table.tbl_expense;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewModelActivityMain extends BaseObservable {

    @Bindable
    public String strExpName, strTodayIn,
            strTodayOut, strDateTime, strTomorrowIn, strTomorrowOut, strExpNameLabel, strExpAmtLabel;

    private String strDate, strTime;

    @Bindable
    public String strExpAmt;

    @Bindable
    public AdapterAutoCompleteTextView adapterAutoCompleteTextView;

    @Bindable
    public ArrayList<clsAutocompleteTextViewRes> alItem;

    @Bindable
    public boolean isChecked = false, isAmountFocus = false;

    private MainActivity activity;

    private roomDatabase database;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdfDate = new SimpleDateFormat(Config.DATE_FORMAT, Locale.ENGLISH);
    private SimpleDateFormat sdfTime = new SimpleDateFormat(Config.TIME_FORMAT, Locale.ENGLISH);
    private int mYear, mMonth, mDay, mHour, mMinute;
    private FirebaseAnalytics mFirebaseAnalytics;

    public ViewModelActivityMain(MainActivity mainActivity) {
        this.activity = mainActivity;
        database = roomDatabase.getContext(activity);
        alItem = new ArrayList<>();
        adapterAutoCompleteTextView = new AdapterAutoCompleteTextView(activity, R.layout.list_autocomplete_textview, alItem);
        strExpNameLabel = "Expense Name";
        strExpAmtLabel = "Expense Amount";
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mainActivity);

        notifyPropertyChanged(BR.strExpNameLabel);
        notifyPropertyChanged(BR.strExpAmtLabel);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "Open Screen");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);

        calculateTodayExpenseAndIncome();
    }

    public void ExpenseEntry() {

        String count = "";
        try {
            count = database.configDao().getLastRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(activity, "" + count, Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(strExpAmt)) {
            Toast.makeText(activity, "Please Enter Expense Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strExpName)) {
            Toast.makeText(activity, "Please Enter Expense Name", Toast.LENGTH_SHORT).show();
            return;
        }

        tbl_expense expense = new tbl_expense(strExpName, strExpAmt, strDate, strTime, String.valueOf(isChecked));

        long aLong = database.expenseDao().InsertData(expense);

        if (aLong > 0) {

            Toast.makeText(activity, "Data save successfully", Toast.LENGTH_SHORT).show();

            strExpAmt = "";
            strExpName = "";

            notifyPropertyChanged(com.kepss.expenseentry.BR.strExpAmt);
            notifyPropertyChanged(com.kepss.expenseentry.BR.strExpName);


            calculateTodayExpenseAndIncome();

        }
    }

    private void calculateTodayExpenseAndIncome() {
        try {
            strTodayOut = PubFun.isNull(database.expenseDao().getExpenseDateWise(sdfDate.format(calendar.getTime()), "false"), "0");
            strTodayIn = PubFun.isNull(database.expenseDao().getExpenseDateWise(sdfDate.format(calendar.getTime()), "true"), "0");

            calendar.add(Calendar.DATE, -1);


            strTomorrowOut = PubFun.isNull(database.expenseDao().getExpenseDateWise(sdfDate.format(calendar.getTime()), "false"), "0");
            strTomorrowIn = PubFun.isNull(database.expenseDao().getExpenseDateWise(sdfDate.format(calendar.getTime()), "true"), "0");

            notifyPropertyChanged(com.kepss.expenseentry.BR.strTodayIn);
            notifyPropertyChanged(com.kepss.expenseentry.BR.strTodayOut);

            notifyPropertyChanged(com.kepss.expenseentry.BR.strTomorrowOut);
            notifyPropertyChanged(com.kepss.expenseentry.BR.strTomorrowIn);

            calendar = Calendar.getInstance();

            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);


            strDate = sdfDate.format(calendar.getTime());

            strTime = sdfTime.format(calendar.getTime());

            strDateTime = strDate + " " + strTime;

            notifyPropertyChanged(com.kepss.expenseentry.BR.strDateTime);

            List<String> alExpenseTemp = database.expenseDao().getAllExpenseName();

            for (String s : alExpenseTemp) {
                alItem.add(new clsAutocompleteTextViewRes(s, s));
            }

            notifyPropertyChanged(com.kepss.expenseentry.BR.alItem);
            Log.e("AG", "calculateTodayExpenseAndIncome: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dateSelect() {


        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String day = "", month = "";
                        if (dayOfMonth < 10) {
                            day = "0" + dayOfMonth;
                        } else {
                            day = String.valueOf(dayOfMonth);
                        }

//                        if ((monthOfYear + 1) < 10) {
//                            month = "0" + monthOfYear;
//                        }

                        if ((monthOfYear + 1) < 10) {
                            month = "0" + (monthOfYear + 1);
                        } else {
                            month = String.valueOf((monthOfYear + 1));
                        }
                        strDate = day + "-" + month + "-" + year;

                        strDateTime = strDate + " " + strTime;
                        notifyPropertyChanged(com.kepss.expenseentry.BR.strDateTime);
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        timePicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void timePicker() {
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        strTime = hourOfDay + ":" + minute;
                        strDateTime = strDate + " " + (hourOfDay + ":" + minute);

                        mHour = hourOfDay;
                        mMinute = minute;

                        notifyPropertyChanged(com.kepss.expenseentry.BR.strDateTime);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void loadReport() {
        activity.startActivity(new Intent(activity, ActivityExpenseReport.class));
    }


    public void autoCompleteClick(AdapterView<?> parent, View view, int position, long id) {
        strExpName = alItem.get(position).name;
        isAmountFocus = true;
        notifyPropertyChanged(com.kepss.expenseentry.BR.strExpName);
        notifyPropertyChanged(com.kepss.expenseentry.BR.isAmountFocus);
    }

    public void vehicleClick() {
        activity.startActivity(new Intent(activity, ActivityVehicleList.class));
    }

    public void changeListener(CompoundButton buttonView, boolean isChecked) {
        this.isChecked = isChecked;

        if (isChecked) {
            strExpNameLabel = "Income Name";
            strExpAmtLabel = "Income Amount";
        } else {
            strExpNameLabel = "Expense Name";
            strExpAmtLabel = "Expense Amount";
        }


        notifyPropertyChanged(BR.isChecked);
        notifyPropertyChanged(BR.strExpNameLabel);
        notifyPropertyChanged(BR.strExpAmtLabel);
    }

    public void onClickWhatsApp() {

        PackageManager pm = activity.getPackageManager();
        try {

            PackageManager packageManager = activity.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);

//            try {
//                String url = "https://api.whatsapp.com/send?phone=917801829449&text=" + URLEncoder.encode("OK", "UTF-8");
//                i.setPackage("com.whatsapp");
//                i.setData(Uri.parse(url));
//                if (i.resolveActivity(packageManager) != null) {
//                    activity.startActivity(i);
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//            }

//            Uri uri = Uri.parse("smsto:9898247417");
//            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//            i.putExtra("sms_body", "OKSDASDAS");
//            i.setPackage("com.whatsapp");
//            i.putExtra(Intent.EXTRA_TEXT, "OKSasd");
//            activity.startActivity(i);
//
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

//            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
//            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            activity.startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (Exception e) {
            Toast.makeText(activity, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

}
