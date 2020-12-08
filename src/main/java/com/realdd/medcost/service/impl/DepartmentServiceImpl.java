package com.realdd.medcost.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.DepartmentMapper;
import com.realdd.medcost.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;


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
        List<Department> list = getDepartListByName(department.getName());
        if (list.size() == 0) {//部门不存在
            return saveOrUpdate(department);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsDelete() == 1 && list.size() > 0) {//list的size>0且状态为1，说明部门存在
                    return false;
                } else if (list.get(i).getIsDelete() == 0) {//部门存在状态为0，修改状态
                    list.get(i).setIsDelete(1);
                    return saveOrUpdate(list.get(i));
                }
            }
        }
        return saveOrUpdate(department);
    }

    //在部门表中根据部门名(name)选择等于name的列表信息
    private List<Department> getDepartListByName(String name) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Department::getName, name);
        return list(wrapper);
    }

    @Override
    public boolean deleteDepartment(Long id) {

        Department depart = getById(id);
        if (depart.getIsDelete() == 1) {
            depart.setIsDelete(0);
        }else{
            return false;
        }

        return saveOrUpdate(depart);
    }

    @Override
    public Page<Department> list(String keyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum, pageSize);
////        UserExample example = new UserExample();
////        UserExample.Criteria criteria = example.createCriteria();
////        if (!StringUtils.isEmpty(keyword)) {
////            criteria.andUsernameLike("%" + keyword + "%");
////            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
////        }
////        return userMapper.selectByExample(example);
        Page<Department> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Department> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(Department::getName, keyword);
            //lambda.or().like(Department::getRealname,keyword);
        }
        return (Page<Department>) page(page, wrapper);
    }

}
