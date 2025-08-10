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

    @GetMapping
    public R<PageVo<Employee>> empList(EmpQueryDto empQueryDto){
        PageVo<Employee> page = employeeService.getEmployeePage(empQueryDto);
        return R.OK(page);
    }

    @PostMapping
    public R<?> exeAdd(@RequestBody EmpDto empDto){
        employeeService.addEmp(empDto);
        return R.OK();
    }

    @PutMapping
    public R<?> exeUpd(@RequestBody EmpDto empDto){
        employeeService.updateEmp(empDto);
        return R.OK();
    }

    @DeleteMapping("/{id}")
    public R<?> exeDel(@PathVariable String id){
        employeeService.removeEmp(id);
        return R.OK();
    }

    @DeleteMapping("")
    public R<?> exeDel(@RequestBody String[] ids){
        employeeService.removeEmp(ids);
        return R.OK();
    }
}
