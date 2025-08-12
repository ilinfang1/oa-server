package org.oaoa.demo.api.info;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.EmpDto;
import org.oaoa.demo.dto.EmpQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.model.Employee;
import org.oaoa.demo.service.info.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor//构造函数注入
@RestController
@RequestMapping("/info/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/dep")
    public R<List<Dep>> allDepList(){
        List<Dep> list = employeeService.getAllEnableDepList();
        return R.OK(list);
    }

    @GetMapping("")
    public R<PageVo<Employee>> empList(EmpQueryDto empQueryDto){
        PageVo<Employee> page = employeeService.getEmployeePage(empQueryDto);
        return R.OK(page);
    }

    @PostMapping("")
    public R<?> execAdd(@RequestBody EmpDto empDto){
        employeeService.addEmp(empDto);
        return R.OK();
    }

    @PutMapping("")
    public R<?> execUpd(@RequestBody EmpDto empDto){
        employeeService.updateEmp(empDto);
        return R.OK();
    }

    @DeleteMapping("/{id}")
    public R<?> execDel(@PathVariable String id){
        employeeService.removeEmp(id);
        return R.OK();
    }

    @DeleteMapping("")
    public R<?> execDel(@RequestBody String[] ids){
        employeeService.removeEmp(ids);
        return R.OK();
    }

    @PutMapping("/{id}/{status}")
    public R<?> changeStatus(@PathVariable String id,@PathVariable Integer status){
        employeeService.changeStatus(id,status);
        return R.OK();
    }

    @PostMapping("/user")
    public R<?> createUser(@RequestBody Map<String,String> userIdMap){
        employeeService.createUser(userIdMap);
        return R.OK();
    }

    @DeleteMapping("/user")
    public R<?> cancelUser(@RequestBody Map<String,String> userIdMap){
        employeeService.cancelUser(userIdMap);
        return R.OK();
    }

    @PutMapping("/leader")
    public R<?> setLeader(@RequestBody Map<String,String> empIdMap){
        employeeService.setLeader(empIdMap);
        return R.OK();
    }
}