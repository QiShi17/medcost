package com.realdd.medcost.service.impl;

import com.realdd.medcost.entity.Hospital;
import com.realdd.medcost.mapper.HospitalMapper;
import com.realdd.medcost.service.HospitalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 指定就诊医院表 服务实现类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-12
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

}
