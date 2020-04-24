package com.shop.web;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jiaxia
 * @date 2020/1/2
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.shop.web", "com.shop.zaqiu", "com.shop.manage", "com.shop.shiro"})
@MapperScan({"com.shop.web.*", "com.shop.zaqiu.*", "com.shop.manage.*", "com.shop.shiro.*"})
public class ShopWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopWebApplication.class, args);
    }
}