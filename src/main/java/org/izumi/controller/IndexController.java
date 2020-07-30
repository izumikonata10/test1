package org.izumi.controller;

import org.izumi.dao.ZhaoMapper;
import org.izumi.model.Zhao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author izumi
 * @date 2020-07-30 08:15
 */
@Controller
public class IndexController {

    @Autowired(required = false)
    ZhaoMapper zhaoMapper;

    @RequestMapping("/test")
    public String home() {
        Zhao z=new Zhao();
        z.setzName("zz");
        z.setzId(22);
        z.setzMoney(22);
        zhaoMapper.insert(z);
        return "index";
    }
}
