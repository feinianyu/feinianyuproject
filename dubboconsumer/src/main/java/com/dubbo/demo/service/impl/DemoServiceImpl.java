package com.dubbo.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
//import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.demo.service.IDemoService;
import com.dubbo.demo.service.TestDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {
    //@Reference(version = "1.0.0")
    @Autowired
    public TestDubboService testDubboService;
    @Override
    public String test() {
        System.out.println("进入接口实现类");
        return testDubboService.helloDubbo();
    }
}
