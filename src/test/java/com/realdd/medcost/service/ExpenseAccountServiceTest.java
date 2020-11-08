package com.realdd.medcost.service;

import com.realdd.medcost.entity.ExpenseAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by duanduan on 2020/11/8 18:34
 */
@SpringBootTest
public class ExpenseAccountServiceTest {
    @Autowired
    ExpenseAccountService expenseAccountService;

    @Test
    void create(){
        ExpenseAccount expenseAccount=new ExpenseAccount();
        expenseAccount.setFHospitalId(1L);
        expenseAccount.setRoom("口腔科");
        expenseAccountService.create(expenseAccount);
    }
}
