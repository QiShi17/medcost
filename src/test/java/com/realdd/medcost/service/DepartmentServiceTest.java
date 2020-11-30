package com.realdd.medcost.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.entity.User;
import io.swagger.models.auth.In;
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
        department.setName("财务处");
        if (departmentService.updateDepartment((long) 9, department)) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void createDepartment() {
        Department department = new Department();
        department.setName("技术处");
        if (departmentService.createDepartment(department)) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    void deleteDepartment() {
        if (departmentService.deleteDepartment((long)13)) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Test
    void listDepartment(){

        Page<Department> list=departmentService.list(null,10,1);
        if(list!=null){
            System.out.println(list.getPages());
            System.out.println(list.getTotal());
            System.out.println(list.getSize());
            System.out.println(list.getCurrent());
            System.out.println(list.getRecords());
        }

    }

}
