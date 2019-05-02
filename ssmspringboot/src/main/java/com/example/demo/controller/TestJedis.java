package com.example.demo.controller;

import com.example.demo.service.ChkAcmService;
import com.example.demo.service.impl.ChkAcmServiceImpl;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class TestJedis {
    public static  void main(String[]args){
       Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.del("test1");
        jedis.del("test2");
        jedis.sadd("test1","1");
        jedis.sadd("test1","2");
        jedis.sadd("test2","2");
        jedis.sadd("test2","3");
        Set<String> set = jedis.sdiff("test1", "test2");
        Iterator<String>it=set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }
        System.out.println("test1:"+jedis.smembers("test1"));
        System.out.println("test2:"+jedis.smembers("test2"));
       // System.out.println(jedis.get("test"));
        /*ChkAcmService chkAcmService=new ChkAcmServiceImpl();
        for (String s1 : chkAcmService.getacmdata()) {
            System.out.println(s1);

        }*/
    }
}
