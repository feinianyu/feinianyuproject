package com.example.demo.service.impl;

import com.example.demo.dao.ChkPayMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.ChkPay;
import com.example.demo.entity.User;
import com.example.demo.service.ChkPayService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChkPayServiceImpl implements ChkPayService {
    @Autowired
    private ChkPayMapper chkPayMapper;//依赖注入
    @Override
    public List<String> getpaydata (){
        return chkPayMapper.findchkpaydata();
    }
}
