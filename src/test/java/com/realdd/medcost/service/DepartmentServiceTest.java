package com.realdd.medcost.service;


import com.realdd.medcost.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentServiceTest {


    @Autowired
    private DepartmentService departmentService;

    @Test
    void updateDepartment() {
        Department department = new Department();
        department.setId((long) 9);
        department.setName("财务部");
        if (departmentService.updateDepartment((long) 9, department)) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

}
