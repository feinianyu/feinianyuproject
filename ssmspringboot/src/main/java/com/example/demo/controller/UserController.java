package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.ChkAcmService;
import com.example.demo.service.ChkPayService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller//声明这是Controller层
public class UserController {
    //依赖注入
    @Autowired
    UserService userService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(User user) {
        //调用dao层
        System.out.println("前端"+user);
        System.out.println("账号"+user.getUsername());
        System.out.println("密码"+user.getPassword());
        User u = userService.getUser(user.getUsername());
        System.out.println("数据库"+u);
        System.out.println("数据库账号"+u.getUsername());
        System.out.println("数据库密码"+u.getPassword());
        if (u.getPassword().equals(user.getPassword())){
            System.out.println("成功验证");
            return "success";
        }
        return "failed";
    }
    /*@Autowired
    StringRedisTemplate stringRedisTemplate;*/
    @Autowired
    ChkPayService  chkPayService;
    @Autowired
    ChkAcmService chkAcmService;
    @RequestMapping(value="/checkdata",method = RequestMethod.POST)
    public String chk_data(){
        /* stringRedisTemplate.opsForValue().set("aaa","111");
       int i=chkPayService.getpaydata().size();
        System.out.println("查询的数据总数是："+i);*/

        Jedis jedis=new Jedis("127.0.0.1",6379);
       // jedis.auth("1234qwer");设置密码

        jedis.del("pay_to_acm");
        System.out.println("第一次获取的值："+jedis.get("pay_to_acm"));
        for (String s : chkPayService.getpaydata()) {
            System.out.println(s);
            jedis.sadd("pay_to_acm",s);
        }
        System.out.println("遍历pay表的值为"+jedis.smembers("pay_to_acm"));//jedis.get("pay_to_acm")取值不可以！
        jedis.del("acm_to_pay");
        System.out.println("第一次获取的值："+jedis.get("acm_to_pay"));
        for (String s1 : chkAcmService.getacmdata()) {
            System.out.println(s1);
            jedis.sadd("acm_to_pay",s1);
        }
        System.out.println("遍历acm表的值为"+jedis.smembers("acm_to_pay"));//jedis.get("pay_to_acm")取值不可以！
        Set<String> sdiff1 = jedis.sdiff("pay_to_acm","acm_to_pay");
        if(sdiff1.isEmpty()){
            System.out.println("无差集");
        }else {
            Iterator<String> it = sdiff1.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        Set<String> sdiff2 = jedis.sdiff("acm_to_pay","pay_to_acm");
        if(sdiff2.isEmpty()){
            System.out.println("无差集");
        }else {
            Iterator<String> it = sdiff2.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        return "checkdata";
    }
    @RequestMapping(value="/checktwoway",method = RequestMethod.POST)
    public String chk_two_way(){

        return "checkdata";
    }
    public static  void main(String[]args){
        User user=new User();
        user.setUsername("xiaofang");
        UserService userService=new UserServiceImpl();
        User u = userService.getUser(user.getUsername());
        System.out.println(u.getPassword());
    }
}
