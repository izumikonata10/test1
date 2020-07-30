package org.izumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Song on 2017/2/15.
 * 官方示例工程中的测试代码
 */
@SpringBootApplication
@MapperScan(basePackages = "org.izumi.dao")
public class IzumiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IzumiApplication.class, args);
    }
}