package org.oaoa.demo.api.security;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.RoleDto;
import org.oaoa.demo.dto.RoleQueryDto;
import org.oaoa.demo.model.Role;

import org.oaoa.demo.service.security.RoleService;
import org.oaoa.demo.vo.MenuVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor// 自动注入,针对finall 变量
@RestController
@RequestMapping("/security/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    public R<PageVo<Role>> roleList(RoleQueryDto roleQueryDto){
        PageVo<Role> page = roleService.getRolePage(roleQueryDto);// 调用业务层
        return R.OK(page);// 返回成功
    }// 获取部门列表
    @PostMapping
    public R<?> execAdd(@RequestBody RoleDto roleDto) {
        roleService.addRole(roleDto);
        return R.OK();
    }

    @PutMapping
    public R<?> execUpd(@RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto);
        return R.OK();
    }
    @DeleteMapping( "/{ro_id}")
    public R<?> execDel(@PathVariable Integer ro_id) {
        roleService.removeRole(ro_id);
        return R.OK();
    }

    @DeleteMapping( "")
    public R<?> execDel(@RequestBody Integer[] ids) {
        roleService.removeRole(ids);
        return R.OK();
    }

    @GetMapping( "/fun")
    public R<List<MenuVo>> allFunList(){
        List<MenuVo> menuVoList = roleService.getFunList();
        return R.OK(menuVoList);
    }

    @GetMapping( "/fun/{roleId}")
    public R<List<Integer>> getRoleFunList(@PathVariable Integer roleId){
        List<Integer> funIdList = roleService.getRoleFunList(roleId);
        return R.OK(funIdList);
    }

    @PutMapping( "/fun")
    public R<?> execUpdateRoleFunIds(@RequestBody Map<String,Object> map) {
        roleService.updateRoleFun(map);
        return R.OK();
    }



}
