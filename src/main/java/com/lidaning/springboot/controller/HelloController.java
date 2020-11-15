package com.lidaning.springboot.controller;

import com.lidaning.springboot.glance.MemoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MemoryInfoService memoryInfoService;

    @Autowired

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello, springboot!";
    }

    @RequestMapping("template")
    public String template(Model model){
        logger.info(" execute template method ... ");
        model.addAttribute("msg", "this is a template");
        return "templates/template";
    }

    @ResponseBody
    @RequestMapping("/memoryInfo")
    public List testMemoryInfo(){
        return memoryInfoService.select(null);
    }

}
