package com.realdd.medcost.mapper;

import com.realdd.medcost.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户所有角色
     */
    List<Role> getRoleListByUserId(@Param("userId")Long userId );

}
