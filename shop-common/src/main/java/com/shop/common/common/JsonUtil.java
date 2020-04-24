package com.shop.common.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/25 0:48
 */
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
