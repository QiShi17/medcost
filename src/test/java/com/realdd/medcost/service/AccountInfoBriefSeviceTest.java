package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by duanduan on 2020/11/16 15:43
 */
@SpringBootTest
public class AccountInfoBriefSeviceTest {
    @Autowired
    AccountInfoBriefSevice accountInfoBriefSevice;

    @Test
    void getAccountInforBriefs(){
        Page<AccountInfoBrief> accountInfoBriefs=accountInfoBriefSevice.getAccountInfoBriefPage(4,1);
        if(accountInfoBriefs!=null){
            System.out.println(accountInfoBriefs.getPages());
            System.out.println(accountInfoBriefs.getTotal());
            System.out.println(accountInfoBriefs.getSize());
            System.out.println(accountInfoBriefs.getCurrent());
            System.out.println(accountInfoBriefs.getRecords());
        }
    }


}
