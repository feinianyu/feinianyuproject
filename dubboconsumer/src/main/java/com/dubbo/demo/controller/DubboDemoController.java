package com.dubbo.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DubboDemoController {

   // @Reference(version = "1.0.0")
   @Autowired(required = false)
    private IDemoService demoService;
    @RequestMapping("/test")
    public void testbefore(){
        System.out.println("进入方法");
        System.out.println("执行调用"+demoService.test());
    }
    @ResponseBody
    public String test() {
        System.out.println("返回值是"+demoService.test());
        return demoService.test();
    }

}
