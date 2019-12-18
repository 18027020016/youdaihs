package com.jxh;

import com.jxh.entity.IdWorker;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;


/*****
 * @Author: 郭文亮
 * @Date: 2019/12/10 10:03
 * @Description: PACKAGE_NAME
 ****/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jxh.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }

}
