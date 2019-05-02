package com.example.demo.dao;

import com.example.demo.entity.ChkAcm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper	//声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface ChkAcmMapper {
    List<String> findchkacmdata();
}
