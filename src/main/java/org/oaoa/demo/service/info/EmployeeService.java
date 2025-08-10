package org.oaoa.demo.service.info;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.EmpDto;
import org.oaoa.demo.dto.EmpQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    PageVo<Employee> getEmployeePage(EmpQueryDto empQueryDto);

    List<Dep> getAllEnableDepList();

    void addEmp(EmpDto empDto);

    void updateEmp(EmpDto empDto);

    void removeEmp(String... ids);//可变参数

//    void changeStatus(Integer id, Integer status);
}
