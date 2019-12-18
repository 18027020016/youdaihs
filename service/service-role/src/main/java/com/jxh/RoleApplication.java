package com.jxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/*****
 * @Author: 郭文亮
 * @Date: 2019/12/18 19:30
 * @Description: com.jxh
 ****/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jxh.dao")
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class,args);
    }
}
