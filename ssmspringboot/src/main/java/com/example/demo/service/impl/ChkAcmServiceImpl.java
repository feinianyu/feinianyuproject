package com.example.demo.service.impl;

import com.example.demo.dao.ChkAcmMapper;
import com.example.demo.entity.ChkAcm;
import com.example.demo.service.ChkAcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChkAcmServiceImpl implements ChkAcmService {
    @Autowired
    private ChkAcmMapper chkacmMapper;//依赖注入
    @Override
    public List<String> getacmdata (){
        return chkacmMapper.findchkacmdata();
    }
}
