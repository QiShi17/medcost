package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.realdd.medcost.dto.AccountResultStatistic;
import com.realdd.medcost.entity.User;

import java.util.List;

/**
 * @Author: Weiser
 * Date: 2020/12/14 0:55
 **/
public interface AccountResultStatisticService extends IService<AccountResultStatistic> {

    List<String> getAllDepartmentAndSchool();

    List<User> getAllUser();
}
