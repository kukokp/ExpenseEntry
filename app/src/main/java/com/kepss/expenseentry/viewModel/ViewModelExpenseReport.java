package com.kepss.expenseentry.viewModel;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.kepss.expenseentry.ActivityExpenseReport;
import com.kepss.expenseentry.adapter.AdapterExpenseReport;
import com.kepss.expenseentry.database.roomDatabase;
import com.kepss.expenseentry.globle.Config;
import com.kepss.expenseentry.table.tbl_expense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewModelExpenseReport extends BaseObservable {

    // adapter
    @Bindable
    public String date, time;
    private tbl_expense dataModel;

    // Activity
    @Bindable
    public String name, strFromDate, strToDate, strTotalExpenseAmt, strTotalIncomeAmt;

    @Bindable
    public final String strFinalFromDate = "from";

    @Bindable
    public final String strFinalToDate = "to";


    @Bindable
    public AdapterExpenseReport adapterExpenseReport;

    @Bindable
    public List<tbl_expense> alItem;

    roomDatabase database;
    ActivityExpenseReport activityExpenseReport;
    int fromDay, fromMonth, fromYear, ToDay, ToMonth, ToYear;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdfDate = new SimpleDateFormat(Config.DATE_FORMAT, Locale.ENGLISH);

    // Adapter
    public ViewModelExpenseReport(tbl_expense dataModel) {
        name = dataModel.getName();
        date = dataModel.getExpDate();
        time = " " + dataModel.getExpTime();
        this.dataModel = dataModel;

        notifyPropertyChanged(com.kepss.expenseentry.BR.name);
        notifyPropertyChanged(com.kepss.expenseentry.BR.date);
        notifyPropertyChanged(com.kepss.expenseentry.BR.time);

    }

    public String getIncomeAmount() {
        if (dataModel.getIsInCome().equalsIgnoreCase("true")) {
            return dataModel.getAmt();
        } else {
            return "0";
        }
    }

    public String getExpAmount() {
        if (dataModel.getIsInCome().equalsIgnoreCase("false")) {
            return dataModel.getAmt();
        } else {
            return "0";
        }
    }

    public void onEditClick(){

    }

    // Activity
    public ViewModelExpenseReport(ActivityExpenseReport activityExpenseReport) {
        this.activityExpenseReport = activityExpenseReport;
        database = roomDatabase.getContext(activityExpenseReport);

        adapterExpenseReport = new AdapterExpenseReport();
        alItem = new ArrayList<>();

        init();
    }

    private void init() {

        fromYear = calendar.get(Calendar.YEAR);

        fromMonth = calendar.get(Calendar.MONTH);

        fromDay = calendar.get(Calendar.DAY_OF_MONTH);

        ToYear = calendar.get(Calendar.YEAR);

        ToMonth = calendar.get(Calendar.MONTH);

        ToDay = calendar.get(Calendar.DAY_OF_MONTH);


        strFromDate = sdfDate.format(calendar.getTime());
        strToDate = sdfDate.format(calendar.getTime());

        notifyPropertyChanged(BR.strFromDate);
        notifyPropertyChanged(BR.strToDate);

        alItem = database.expenseDao().selectDataDateWise(strFromDate, strToDate);
        setTotal();
        notifyPropertyChanged(BR.alItem);

    }

    private void setTotal() {
        double income = 0.0;
        double outCome = 0.0;

        for (tbl_expense expense :
                alItem) {

            if (expense.getIsInCome().equalsIgnoreCase("true")) {
                income = income + Double.parseDouble(expense.getAmt());
            } else {
                outCome = outCome + Double.parseDouble(expense.getAmt());
            }
        }

        strTotalExpenseAmt = String.valueOf(outCome);
        strTotalIncomeAmt = String.valueOf(income);

        notifyPropertyChanged(BR.strTotalExpenseAmt);
        notifyPropertyChanged(BR.strTotalIncomeAmt);
    }


    private void updateData() {
        alItem = database.expenseDao().selectDataDateWise(strFromDate, strToDate);

        notifyPropertyChanged(BR.alItem);
        setTotal();

    }

    public void selectDate(String strType) {
        try {
            if (strType.equalsIgnoreCase(strFinalFromDate)) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(activityExpenseReport,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String strDay = "", strMonth = "";
                                if (dayOfMonth < 10) {
                                    strDay = "0" + dayOfMonth;
                                } else {
                                    strDay = String.valueOf(dayOfMonth);
                                }

                                if ((monthOfYear + 1) < 10) {
                                    strMonth = "0" + (monthOfYear + 1);
                                } else {
                                    strMonth = String.valueOf((monthOfYear + 1));
                                }

                                strFromDate = strDay + "-" + strMonth + "-" + year;

                                fromYear = year;

                                fromMonth = monthOfYear;

                                fromDay = dayOfMonth;

                                notifyPropertyChanged(BR.strFromDate);
                                updateData();
                            }
                        }, fromYear, fromMonth, fromDay);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            } else {


                DatePickerDialog datePickerDialog = new DatePickerDialog(activityExpenseReport,
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

                                if ((monthOfYear + 1) < 10) {
                                    month = "0" + (monthOfYear + 1);
                                } else {
                                    month = String.valueOf((monthOfYear + 1));
                                }

                                strToDate = day + "-" + month + "-" + year;

                                ToYear = year;

                                ToMonth = monthOfYear;

                                ToDay = dayOfMonth;

                                notifyPropertyChanged(BR.strToDate);
                                updateData();
                            }
                        }, ToYear, ToMonth, ToDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
