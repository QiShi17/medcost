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
    AccountInfoBriefService accountInfoBriefService;

    @Test
    void getAccountInforBriefs(){
        Page<AccountInfoBrief> accountInfoBriefs= accountInfoBriefService.getAccountInfoBriefPage(4,1);
        if(accountInfoBriefs!=null){
            System.out.println(accountInfoBriefs.getPages());
            System.out.println(accountInfoBriefs.getTotal());
            System.out.println(accountInfoBriefs.getSize());
            System.out.println(accountInfoBriefs.getCurrent());
            System.out.println(accountInfoBriefs.getRecords());
        }
    }

    @Test
    void getAccountInforBriefs2(){
        Page<AccountInfoBrief> accountInfoBriefs= accountInfoBriefService.getAccountInfoBriefPageReview(10,1,"123456789",6,"123456789");
        if(accountInfoBriefs!=null){
            System.out.println(accountInfoBriefs.getPages());
            System.out.println(accountInfoBriefs.getTotal());
            System.out.println(accountInfoBriefs.getSize());
            System.out.println(accountInfoBriefs.getCurrent());
            System.out.println(accountInfoBriefs.getRecords());
        }
    }


}
