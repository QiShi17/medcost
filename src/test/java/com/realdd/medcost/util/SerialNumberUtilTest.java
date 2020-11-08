package com.realdd.medcost.util;

import com.realdd.medcost.common.utils.SerialNumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by duanduan on 2020/11/8 18:03
 */
@SpringBootTest
public class SerialNumberUtilTest {
    @Autowired
    SerialNumberUtil serialNumberUtil;

    @Test
    void getSerialNumber(){
        System.out.println(serialNumberUtil.getSerialNumber());
    }
}
