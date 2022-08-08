package com.limitway.current.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DictionaryUtils {

    public static void main(String[] args) {

        Map map = new HashMap<String,String>();
        Type[] st = map.getClass().getGenericInterfaces();
        for (int i = 0; i < st.length; i++) {
            System.out.println(((ParameterizedType)st[i]).getActualTypeArguments());
        }
    }

    public static void math(){
        Map map = new HashMap<String,String>();
    }
}
