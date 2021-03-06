package com.caloger.stasher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class ApplicationController {

    /**
     * @return frontend
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }


    /**
     * @return forward to handle React router
     */
    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}