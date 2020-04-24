package com.shop.zaqiu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.common.common.*;
import com.shop.common.enums.ResultEnum;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.service.impl.UserServiceImpl;
import com.shop.zaqiu.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;
import java.util.UUID;


/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.controller
 * @date : 2019/10/28 14:51
 */
@RestController
@RequestMapping("/wx/user")
@Slf4j
public class WxMaUserController {
    /**
     * 登陆接口
     */

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ResultVO login(@RequestParam("code") String code,
                          @RequestParam("EncryptedData") String EncryptedData,
                          @RequestParam("Iv") String Iv,
                          @RequestParam("detail") String detail) {


        String APPID = "wxb0feded4d7d7aad2";//wx8886f23ea6db4812
        String SECRET = "541ee1165c79e2ef8db5e2b7a8887091";//53eb6a4ac7ffcf7b8bac1271cac14ebc
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response");
        log.info(response);
        Code2SessionVo code2SessionVo = JSON.parseObject(response, Code2SessionVo.class);

        //用户非敏感信息rawData
        UserInfoVo userInfoVo = JSONObject.parseObject(detail, UserInfoVo.class);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", code2SessionVo.getOpenid());
        User user = userService.getOne(queryWrapper);

        if (user == null) {
            //保存一个用户

            //解密用户敏感信息，后面再加
            String str="";
            try {
                str = getWxMiniPhone(code2SessionVo.getSession_key(),Iv,EncryptedData);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject userInfo = JSON.parseObject(str);

          //  System.out.println("【用户信息】"+userInfo.toString());
            User user2 = new User();
            user2.setOpenId(code2SessionVo.getOpenid());
            if (userInfoVo != null) {
                user2.setSex(userInfoVo.getGender());
                user2.setArea(userInfoVo.getCity());
                user2.setNick(userInfoVo.getNickName());
                user2.setHeadUrl(userInfoVo.getAvatarUrl());
                user2.setPhone(str);
            }
            log.info(user2.toString());
            userService.save(user2);
        }
//
        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        log.info(token);
        log.info(code2SessionVo.getOpenid());
        redisTemplate.opsForValue().set(token, code2SessionVo.getOpenid());
        return ResultVOUtil.success(token);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        return ResultEnum.LOGOUT_SUCCESS.getMessage();
    }


    /**
     * 功能描述: <br> 微信小程序解析手机号码 </br>
     * @Param: [sessionkey, iv, encryptedData]
     * @Return: java.lang.String
     * @Author: roc_wl
     * @Date: 2019/9/26 16:25
     */
    public static String getWxMiniPhone(String sessionkey, String iv, String encryptedData)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        byte[] encrypData = Base64Utils.decodeFromString(encryptedData);
        byte[] ivData = Base64Utils.decodeFromString(iv);
        byte[] sessionKey = Base64Utils.decodeFromString(sessionkey);
        String resultString = null;
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        } catch (Exception e) {

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        }

        JSONObject object = JSONObject.parseObject(resultString);
        // 拿到手机号码
        String phone = object.getString("phoneNumber");
        return phone;
    }


}
