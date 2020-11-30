package com.realdd.medcost.common.utils;

import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * Created by duanduan on 2020/11/27 16:38
 */
@Component
public class UserInfoUtil {
    @Autowired
    UserService userService;
    public User getUserInfo(Principal principal){
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        return user;
    }
}
