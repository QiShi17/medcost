package com.realdd.medcost.service;

import com.realdd.medcost.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
public interface RoleService extends IService<Role> {
    /**
     * 获取用户所有角色
     */
    List<Role> getRoleListByUserId(Long userId );

}
