package com.shop.zaqiu.vo;

import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/10/29 9:10
 */
@Data
public class Code2SessionVo {

    private String openid;
    //会话密钥
    private String session_key;
    //用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
    private String unionid;
    //错误码
    private String errcode;
    //错误信息
    private String errmsg;


}
