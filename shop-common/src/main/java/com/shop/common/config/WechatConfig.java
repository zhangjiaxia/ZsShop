package com.shop.common.config;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.config
 * @date : 2019/11/2 15:24
 */
public class WechatConfig {
    //小程序appid
    public static final String appid = "wxb0feded4d7d7aad2";//wx8886f23ea6db4812
    //微信支付的商户id,微信支付商户平台的登录账号，https://pay.weixin.qq.com/index.php/extend/employee
    public static final String mch_id = "1560309651";
    //微信支付的商户密钥

    public static final String key = "dsafsdfdfsd465456dsfjholyfvfdgds";
    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String notify_url = "https://game.itaocow.com.cn/pay/wxNotify";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
