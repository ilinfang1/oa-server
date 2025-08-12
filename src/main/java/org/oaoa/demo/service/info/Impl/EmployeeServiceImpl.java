package org.oaoa.demo.service.info.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.oaoa.demo.common.InfoStatusEnum;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dao.EmployeeDao;
import org.oaoa.demo.dto.EmpDto;
import org.oaoa.demo.dto.EmpQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.model.Employee;
import org.oaoa.demo.service.info.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    public PageVo<Employee> getEmployeePage(EmpQueryDto empQueryDto) {
        return PageVo.getPageVo(empQueryDto,()->employeeDao.findEmployeeList(empQueryDto));
    }

    @Override
    public List<Dep> getAllEnableDepList() {
        return employeeDao.findAllEnableDepList();
    }

    private int id;

    @PostConstruct
    public void init(){
        id = employeeDao.findMaxNumberId();
    }

    private synchronized String newId(){
        return String.format("%05d",++id);
    }

    @Override
    public void addEmp(EmpDto empDto) {
        empDto.setE_id(newId());
        empDto.setE_status(InfoStatusEnum.undetermined.getCode());
        employeeDao.insertEmp(empDto);
    }

    @Override
    public void updateEmp(EmpDto empDto) {
        employeeDao.updateEmp(empDto);
    }

    @Override
    public void removeEmp(String... ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException("请选择要删除的部门");

        }
        // 如果被删除的数据中存在的状态不是“未确定”的，则抛出异常并阻止
        boolean exists = employeeDao.findExistsUndetermined(ids);
        if (exists) {
            throw new BusinessException("存在状态“已确定”的部门，请重新选择");
        }
        employeeDao.deleteEmp(ids);
    }

    @Override
    public void changeStatus(String id, Integer status) {
        //如果status为禁用状态，还需进行撤销账号操作和删除领导设置
        if(InfoStatusEnum.disabled.getCode().equals(status)){
            Map<String, String> empIdMap = new HashMap<>();
            empIdMap.put("empId",id);
            this.cancelUser(empIdMap);//撤销账号

            //删除领导设置
            employeeDao.deleteLeaderByEmpId(id);
        }
        employeeDao.updateStatus(id,status);
    }

    @Override
    public void createUser(Map<String, String> userIdMap) {
        employeeDao.insertUserByEmpId(userIdMap);
    }

    @Override
    public void cancelUser(Map<String, String> userIdMap) {
        employeeDao.deleteUserRoles(userIdMap);
        employeeDao.deleteUserIdForEmp(userIdMap);
    }

    @Override
    public void setLeader(Map<String, String> empIdMap) {
        employeeDao.deleteLeader(empIdMap);//删除员工所在部门已有领导设置
        employeeDao.insertLeader(empIdMap);//设置员工为所在部门领导
    }

}
