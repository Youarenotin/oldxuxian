package com.ab.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youarenotin on 16/7/26.
 */
public class JsonHelper {

    public static <T> T parseObjectByJsonStr(String string , Class<T> clazz){
        T object = JSON.parseObject(string,clazz);
        if (object==null)
            return null;
        return object;
    }

    public static <T> List<T> parseListByJsonStr(String string, Class<T> clazz){
        List<T> list ;
        list = JSON.parseArray(string,clazz);
        if (list==null)
            return null;
        return list ;
    }

    public static  List<Map<String,Object>> parseListKeyMapByJsonStr(String string){
        try {
            return JSON.parseObject(string,new TypeReference<List<Map<String, Object>>>(){},new Feature[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String,Object> parseMapByJsonStr(String string){
        try {
            return JSON.parseObject(string, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateJsonStrByObject(Object obj){
        try {
            return JSON.toJSONString(obj,true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
