package com.weibo.ddd;

import com.weibo.poto.spring.EnablePoto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Application
 * @Author hebiao1
 * @Date 2021/9/1 4:07 下午
 * @Version 1.0
 */
@Configuration

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.weibo.ddd"})
@EnablePoto
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
