package com.indev.ruraldevelopment.Activity.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CommonClass {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    static SharedPrefHelper sharedPrefHelper;

    public static boolean isInternetOn(Context context) {
        sharedPrefHelper=new SharedPrefHelper(context);
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connec != null;
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED
                || connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;

        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;

    }

    public static String getUUID() {
        Date date = new Date();
//        sharedPrefHelper.setString("uuid",getUUID());

        //Calendar calUUID = Calendar.getInstance();
        DateFormat dateFormatUUID = new SimpleDateFormat("yyyyddmmss");
        String uuid= ((SimpleDateFormat) dateFormatUUID).format(date);

        return uuid;
    }


    public static String currentDate() {
        Date date = new Date();
        //Calendar calUUID = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.format(date);
    }
}
