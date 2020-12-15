package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realdd.medcost.entity.Department;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author huangxiaohou
 * @since 2020-11-12
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    List<String> selectAllDepartmentAndSchool();


}
