package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserByUsername(){
        User user=userService.getUserByUsername("2020212029");
        System.out.println(user);
    }

    @Test
    void getUserByUsernameNull(){
        User user=userService.getUserByUsername("2020212020");
        System.out.println(user);
    }

    @Test
    void insertUserIdTpyeAuto(){
        User user=userService.getUserByUsername("12345678901234");
        if(user==null){
            System.out.println("hello");
            user=new User();
            user.setUsername("12345678901234");
            userService.insertUserIdTypeAuto(user);
            System.out.println(user);
        }
    }

    @Test
    void list(){
        Page<User> pageUser=userService.list(null,10,1);
        if(pageUser!=null){
            System.out.println(pageUser.getPages());
            System.out.println(pageUser.getTotal());
            System.out.println(pageUser.getSize());
            System.out.println(pageUser.getCurrent());
            System.out.println(pageUser.getRecords());
        }
    }

    @Test
    void testInsertUserDuplicate(){
        User user=new User();
        user.setUsername("pli");
        user.setAge(66);
        userMapper.insertUserDuplicate(user);
    }

    @Test
    void testInsertBatchUserDuplicate(){
        User user=new User();
        user.setUsername("pli");
        user.setAge(98);
        user.setGender(0);
        user.setPassword("string");
        List<User> userList=new ArrayList<>();
        userList.add(user);
        User user2=new User();
        user2.setUsername("jiesfjweio");
        user2.setRealname("踩踩过广告");
        userList.add(user2);
        userService.insertBatchUserDuplicateEncodePwd(userList);
    }
}
