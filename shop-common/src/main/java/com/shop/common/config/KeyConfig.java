package com.shop.common.config;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.config
 * @date : 2019/10/25 10:15
 */
public class KeyConfig {
    public static final String accessKey = "Splfr4Fz2uRwIEhRrM4LLprWnbqbmOMt2XXjnWuZ";
    public static final String secretKey = "cGtqDrEQRmylEz9FNunM1V64d5mOdEUpvYaMlJB0";
    public static final String PicBucket = "public";
    public static final String FileBucket = "zaqiugame";

    public static final String PicDns = "http://cloud.itaocow.com.cn";
    public static final String FileDns = "http://private.itaocow.com.cn";

    //盐
    public static final String salt = "mnokas@￥%……dhgnkavn￥#&#%*kashg";

    public static final String headToken = "Authorization";

    //设置下载链接过期时间,默认8个小时
    public static Integer UrlTimeOut = 28800;

    //短信过期时间,分钟
    public static final Integer SmsTimeOut = 5;

    //登录token过期时间，天
    public static final Integer TokenOut = 30;

    /*
     * 扫码获取的uuid过期时间,分钟
     */
    public static final Integer UUIDTimeOut = 5;

    //绑定微信
    public static final Integer BINDWX = 100;
    //通知下载
    public static final Integer DOWN = 101;
}
