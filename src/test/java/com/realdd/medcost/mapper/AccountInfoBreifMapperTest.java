package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by duanduan on 2020/11/16 15:28
 */
@SpringBootTest
public class AccountInfoBreifMapperTest {
    @Autowired
    AccountInfoBriefMapper accountInfoBriefMapper;

    @Test
    void list(){
        Page<AccountInfoBrief> accountInfoBriefPage = new Page<>(1,4);
        Page<AccountInfoBrief> accountInfoBriefs=accountInfoBriefMapper.userAndAccountInfoPage(accountInfoBriefPage);
        if(accountInfoBriefs!=null){
            System.out.println(accountInfoBriefs.getPages());
            System.out.println(accountInfoBriefs.getTotal());
            System.out.println(accountInfoBriefs.getSize());
            System.out.println(accountInfoBriefs.getCurrent());
            System.out.println(accountInfoBriefs.getRecords());
        }
    }


}
