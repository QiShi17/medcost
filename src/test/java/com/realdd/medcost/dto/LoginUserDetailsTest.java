package com.realdd.medcost.dto;

import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.RoleService;
import com.realdd.medcost.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class LoginUserDetailsTest {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Test
    void getAuthorities(){
        User user=userService.getUserByUsername("xwz");
        List<Role> roles= roleService.getRoleListByUserId(user.getId());
        LoginUserDetails loginUserDetails=new LoginUserDetails(user,roles);
        Collection<? extends GrantedAuthority> authorities=loginUserDetails.getAuthorities();
        for(GrantedAuthority g:authorities){
            System.out.println(g);
        }
    }
}
