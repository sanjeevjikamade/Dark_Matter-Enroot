package com.dark.matter.railapp.common;

import android.app.IntentService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nayana on 2/1/18.
 */

public class Utilities {

    private ProgressBar progressView;

    private static final String patternInt = "0123456789";
    private static final String patternString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom random = new SecureRandom();
    public static final String API_KEY = "AIzaSyAXdY56La8YHXRl7SVE30UlUup_nqXJ0uU";
    public static String YOUTUBE_CODE = "mtTMQUWmESs";
    public static String YOUTUBE_URL = "http://www.youtube.com/watch?v=w_lrBpLyayM";
    public static String[] questionnaire_types = {"HEALTH", "LIFESTYLE", "NUTRITION", "ACTIVITY AND EXERCISE"};
    public static String toast_no_internet = "Please connect to internet!";
    public static String toast_no_response = "Unable get response from server,please try after sometime";
    public static String toast_something_went_wrong = "Something went wrong";
    public static String toast_respnse_fail = "Could not get response from server!";
    public static String toast_data_null = "data null";
    public static String toast_feature_disabled = "Buy Multiply";

    public static boolean isDialogShowing = false;


    public Utilities(Context context) {
        progressView = new ProgressBar(context);
    }

    public void hideProgress(View view) {
        view.setVisibility(View.GONE);
    }

    public void showProgress(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        //final String PASSWORD_PATTERN = "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$/";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isValidEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        //final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String EMAIL_PATTERN = "(?:[\\p{L}0-9!#$%\\&'*+/=?\\^_`{|}~-]+(?:\\.[\\p{L}0-9!#$%\\&'*+/=?\\^_`{|}" +
                "~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\" +
                "x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[\\p{L}0-9](?:[a-" +
                "z0-9-]*[\\p{L}0-9])?\\.)+[\\p{L}0-9](?:[\\p{L}0-9-]*[\\p{L}0-9])?|\\[(?:(?:25[0-5" +
                "]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-" +
                "9][0-9]?|[\\p{L}0-9-]*[\\p{L}0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21" +
                "-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }


    public static boolean isValidUsername(String username) {
        Pattern pattern;
        Matcher matcher;
        final String USERNAME_PATTERN = "^[a-zA-Z0-9._.@-]+$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isValidName(String username) {
        Pattern pattern;
        Matcher matcher;
        final String NAME_PATTERN = "[0-9]";
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(username);

        return matcher.matches();
    }


    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static boolean isInternetAvailable(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                return true;
            } else {

                if (context instanceof IntentService) {
                    return false;
                }

                //No internet
                Toast.makeText(context, "Please connect to internet!", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {

            if (context instanceof IntentService) {
                return false;
            }
            //No internet
            Toast.makeText(context, "Please connect to internet!", Toast.LENGTH_LONG).show();

            return false;
        }
    }
    /*public static boolean isInternetAvailable(Context context) {
        boolean flag = false;
        try {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();

            if (netInfo != null) {
                if (netInfo.isConnected()) {
                    // Internet Available
                    flag = true;
                } else {
                    //No internet
                    flag = false;
                }
            } else {
                //No internet
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }*/


    public static String convertDateToString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        String date_string = df.format(date);
        return date_string;
    }

    public static Date convertStringToDate(String date_string, String format) {

        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(date_string);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String getRandomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(patternString.charAt(random.nextInt(patternString.length())));
        return sb.toString();
    }

    public static int getRandomInt(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(patternInt.charAt(random.nextInt(patternInt.length())));
        return Integer.parseInt(sb.toString());

    }

    public static long getRandomLong(long len) {
        int len_ = (int) len;
        StringBuilder sb = new StringBuilder(len_);
        for (int i = 0; i < len_; i++)
            sb.append(patternInt.charAt(random.nextInt(patternInt.length())));
        return Long.valueOf(sb.toString());

    }


    public static void showToastMessage(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


}
