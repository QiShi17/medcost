package com.realdd.medcost.controller;


import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.service.HospitalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 指定就诊医院表 前端控制器
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @ApiOperation("获取全部医院信息")
    @GetMapping(value = "/get_list_all")
    public CommonResult getListAll(){
        return CommonResult.success(hospitalService.list());
    }
}
