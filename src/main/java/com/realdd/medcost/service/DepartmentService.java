package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.entity.User;

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
    boolean deleteDepartment(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<Department> list(String keyword, Integer pageSize, Integer pageNum);

}
