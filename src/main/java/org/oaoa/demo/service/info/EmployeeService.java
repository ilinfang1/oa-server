package org.oaoa.demo.service.info;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.EmpDto;
import org.oaoa.demo.dto.EmpQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    PageVo<Employee> getEmployeePage(EmpQueryDto empQueryDto);

    List<Dep> getAllEnableDepList();

    void addEmp(EmpDto empDto);

    void updateEmp(EmpDto empDto);

    void removeEmp(String... ids);//可变参数

    void changeStatus(String id, Integer status);

    void createUser(Map<String, String> userIdMap);


    void cancelUser(Map<String, String> userIdMap);

    void setLeader(Map<String, String> empIdMap);

}
