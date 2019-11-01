package com.kepss.expenseentry.globle;

import android.text.TextUtils;

public class PubFun {

    public static String isNull(String s, String strDefult) {
        if (s == null) {
            return strDefult;
        }
        if (TextUtils.isEmpty(s)) {
            return strDefult;
        } else {
            return s;
        }
    }
}
