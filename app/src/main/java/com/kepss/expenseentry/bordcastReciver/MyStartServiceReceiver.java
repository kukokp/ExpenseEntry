package com.kepss.expenseentry.bordcastReciver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.kepss.expenseentry.service.Util;

public class MyStartServiceReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Util.scheduleJob(context);
    }
}