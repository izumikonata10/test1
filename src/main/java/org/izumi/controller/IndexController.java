package org.izumi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author izumi
 * @date 2020-07-30 08:15
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "/index";
    }
}
