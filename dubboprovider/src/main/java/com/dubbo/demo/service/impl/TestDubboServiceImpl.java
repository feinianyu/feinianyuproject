package com.dubbo.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.demo.service.TestDubboService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",timeout = 10000,interfaceClass = TestDubboService.class)
public class TestDubboServiceImpl implements TestDubboService {
    @Override
    public String helloDubbo() {
        return "hello dubbo !";
    }
}
