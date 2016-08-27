package com.xuxian.marketpro.libraries.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import com.xuxian.marketpro.presentation.View.widght.custom.PullToRefreshView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by youarenotin on 16/8/27.
 */
public class Tools {
    public static final int CODE_FAILURE = 1;
    public static final int CODE_SUCCESS = 0;
    public static final int TYPE_IS_FROM_NET = 111;
    public static final int TYPE_IS_FROM_PULL = 333;
    public static final int TYPE_IS_FROM_PULLUP = 222;
    public static final int TYPE_IS_REFRESH = 444;

    public static boolean isEmail(String mobiles) {
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(mobiles).matches();
    }

    public static boolean isFormat(String mobiles) {
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{0,}$");
        Pattern p1 = Pattern.compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$");
        return p.matcher(mobiles).matches();
    }

    public static boolean isMobileNO(String mobiles) {
        return Pattern.compile("^1[3,5,6,7,8,2][0-9]{9}$").matcher(mobiles).matches();
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static boolean isObj(Object object) {
        if (object == null || "" == object || " " == object || "null" == object || "[]" == object) {
            return true;
        }
        return false;
    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(AbDateUtil.dateFormatYMDHMS);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        return Integer.parseInt(String.valueOf((cal.getTimeInMillis() - time1) / PullToRefreshView.ONE_DAY));
    }

    public static String printDifference(String endTiem, String currentTiem) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AbDateUtil.dateFormatYMDHMS);
        long different = (simpleDateFormat.parse(endTiem).getTime() - simpleDateFormat.parse(currentTiem).getTime()) - 1000;
        long minutesInMilli = 1000 * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        int tian = Integer.parseInt(String.valueOf(different / PullToRefreshView.ONE_DAY));
        long elapsedDays = different / daysInMilli;
        different %= daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different %= hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different %= minutesInMilli;
        long elapsedSeconds = different / 1000;
        if (different < 0) {
            return null;
        }
        return tian + "\u5929" + elapsedHours + "\u65f6" + elapsedMinutes + "\u5206" + elapsedSeconds + "\u79d2";
    }

    public static int Day(String completion_time, long currentTiem) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(AbDateUtil.dateFormatYMDHM);
            return Integer.parseInt(String.valueOf((format.parse(format.format(Long.valueOf(currentTiem))).getTime() - format.parse(completion_time).getTime()) / ((long) 86400000)));
        } catch (ParseException e) {
            return 3;
        }
    }

    public Drawable convertBitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static String getDecimalFormat(Double price) {
        return new DecimalFormat("0.00").format(price);
    }

    public static String getImg(String newimg) {
        if (AbStrUtil.isEmpty(newimg)) {
            return newimg;
        }
        return newimg.replace(".jpg", "_464_261.jpg");
    }

    public static String readAssetTxt(Context context, String fileName) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
            LineNumberReader reader = new LineNumberReader(bufReader);
            String s = reader.readLine();
            String result = "";
            int lines = CODE_SUCCESS;
            while (s != null) {
                lines += CODE_FAILURE;
                s = reader.readLine();
                result = result + s;
            }
            reader.close();
            bufReader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void adaptWh(View view, int width, int height) {
        ViewGroup.LayoutParams lp1 = view.getLayoutParams();
        lp1.width = width;
        lp1.height = height;
        view.setLayoutParams(lp1);
    }
}
