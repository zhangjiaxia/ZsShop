package com.shop.common.common;

import java.util.Random;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/25 0:48
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number) ;
    }

    public static synchronized Integer genOderId(){
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        return Integer.parseInt(timestamp);
    }
}
