package org.izumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Song on 2017/2/15.
 * 官方示例工程中的测试代码
 */
@SpringBootApplication(scanBasePackages = "org.izumi")
@MapperScan(basePackages = "org.izumi.dao")
@Component
@Configuration
public class IzumiApplication {
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个文件大小
        factory.setMaxFileSize("50240KB");
        /// 总上传文件大小
        factory.setMaxRequestSize("202400KB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IzumiApplication.class, args);
    }
}