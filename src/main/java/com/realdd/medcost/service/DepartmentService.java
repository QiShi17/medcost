package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.realdd.medcost.entity.Department;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author huangxiaohou
 * @since 2020-11-12
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 修改部门名称
     */
    boolean updateDepartment(Long id,Department department);

    /**
     * 添加部门
     * @param department
     * @return
     */
    boolean createDepartment(Department department);

    /**
     * 删除部门
     */
    boolean deleteDepartment(String name);

}
