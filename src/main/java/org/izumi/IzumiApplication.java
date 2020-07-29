package org.izumi;

import org.izumi.dao.ZhaoMapper;
import org.izumi.model.Zhao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Song on 2017/2/15.
 * 官方示例工程中的测试代码
 */
@SpringBootApplication
@MapperScan(basePackages = "org.izumi.dao")
@Controller
public class IzumiApplication {
    @Resource
    ZhaoMapper zhaoMapper;

    @RequestMapping(value = "/")
    @ResponseBody
    Zhao home() {
        Zhao i = new Zhao();
        i.setzId(2);
        i.setzMoney(11);
        i.setzName("11111");
        zhaoMapper.insert(i);
        return i;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IzumiApplication.class, args);
    }
}