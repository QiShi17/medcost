package com.realdd.medcost.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realdd.medcost.dto.AccountResultStatistic;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.AccountResultStatisticMapper;
import com.realdd.medcost.mapper.DepartmentMapper;
import com.realdd.medcost.mapper.UserMapper;
import com.realdd.medcost.service.AccountResultStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: Weiser
 * Date: 2020/12/14 0:59
 **/
@Service
public class AccountResultStatisticServiceImpl extends ServiceImpl<AccountResultStatisticMapper, AccountResultStatistic> implements AccountResultStatisticService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<String> getAllDepartmentAndSchool() {
        return departmentMapper.selectAllDepartmentAndSchool();
    }

    public List<User> getAllUser()
    {
        return userMapper.selectAll();
    }


}
