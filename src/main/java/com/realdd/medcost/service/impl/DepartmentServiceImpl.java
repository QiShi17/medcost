package com.realdd.medcost.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.mapper.DepartmentMapper;
import com.realdd.medcost.service.DepartmentService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author huangxiaohou
 * @since 2020-11-12
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public boolean updateDepartment(Long id, Department department) {
        department.setId(id);
        Department depart = getById(id);
        if (depart.getIsDelete() == 1 && !depart.getName().equals(department.getName())) {
            depart.setName(department.getName());
        }
        boolean success = updateById(depart);
        return success;
    }


    @Override
    public boolean createDepartment(Department department) {
        return false;
    }

    @Override
    public boolean deleteDepartment(String name) {
        return false;
    }
}
