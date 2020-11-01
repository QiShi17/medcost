package com.realdd.medcost.dto;

import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginUserDetails  implements UserDetails {
    private User user;
    private List<Role> roleList;
    public LoginUserDetails(User user, List<Role> roleList) {
        this.user = user;
        this.roleList=roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return roleList.stream()
                .filter(role -> role.getValue()!=null)
                .map(role ->new SimpleGrantedAuthority("ROLE_"+role.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
