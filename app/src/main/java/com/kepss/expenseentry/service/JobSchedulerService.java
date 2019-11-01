package com.kepss.expenseentry.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.kepss.expenseentry.database.roomDatabase;
import com.kepss.expenseentry.globle.Config;
import com.kepss.expenseentry.table.tbl_config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JobSchedulerService extends JobService {
    private static final String TAG = "SyncService";
    private roomDatabase database;
    Calendar calendar;

    private SimpleDateFormat sdfDate = new SimpleDateFormat(Config.DATE_TIME_FORMAT, Locale.ENGLISH);


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onStartJob(JobParameters params) {
        if (database == null) {
            database = roomDatabase.getContext(this);
        }
        if (calendar != null) {
            calendar = Calendar.getInstance();
        }

        String value = database.configDao().getLastRecord();
        if (TextUtils.isEmpty(value)) {
            value = "1";
        } else {
            value = String.valueOf(Integer.parseInt(value) + 1);
        }

        String date = sdfDate.format(calendar.getTime());
        database.configDao().insertData(new tbl_config(value,date));

        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onStopJob: ");
        return true;
    }

}
