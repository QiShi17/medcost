package com.realdd.medcost.common.utils;

import cn.hutool.core.date.format.FastDateFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Created by duanduan on 2020/11/8 17:42
 */
@Component
public class SerialNumberUtil {


    public String getSerialNumber(){
        //1.获取当前时间
        String t=FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
        //2.获取3位随机数
        int x=(int)(Math.random()*900)+100;
        return t+x;

    }
}
