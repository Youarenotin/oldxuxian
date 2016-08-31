package com.xuxian.marketpro.libraries.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by youarenotin on 16/8/31.
 */
public class AppJsonFileReader {
    public static String getJson(Context context,String fileName){
        StringBuilder sb =new StringBuilder();
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while(( str=br.readLine())!=null){
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
