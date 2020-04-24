package com.shop.common.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/24 16:57
 */
public class ResultVOUtil {
    static Gson g = new GsonBuilder().create();

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    //websocket返回格式
    public static String SuccessWebSocket(Object object,Integer code) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(code);
        resultVO.setMsg("成功");

        String str = g.toJson(resultVO);
        return str;
    }
}