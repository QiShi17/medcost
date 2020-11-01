package com.realdd.medcost.service;

import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    void getRoleListByUserId(){
        List<Role> roles= roleService.getRoleListByUserId((long)37);
        for(Role r:roles){
            System.out.println(r);
        }

    }

}
