package com.realdd.medcost.service.impl;

import com.realdd.medcost.entity.Role;
import com.realdd.medcost.mapper.RoleMapper;
import com.realdd.medcost.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoleListByUserId(Long userId) {
        return this.roleMapper.getRoleListByUserId(userId);
    }
}
