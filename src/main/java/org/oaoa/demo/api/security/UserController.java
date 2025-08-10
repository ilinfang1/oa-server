package org.oaoa.demo.api.security;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.UserDto;
import org.oaoa.demo.dto.UserQueryDto;
import org.oaoa.demo.model.Role;
import org.oaoa.demo.model.User;
import org.oaoa.demo.service.security.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor// 自动注入,针对finall 变量
@RestController
@RequestMapping("/security/user")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public R<PageVo<User>> userList(UserQueryDto userQueryDto){
        PageVo<User> page = userService.getUserPage(userQueryDto);// 调用业务层
        return R.OK(page);// 返回成功
    }// 获取部门列表
    @PostMapping
    public R<?> execAdd(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return R.OK();
    }

    @PutMapping
    public R<?> execUpd(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return R.OK();
    }
    @DeleteMapping( "/{ro_id}")
    public R<?> execDel(@PathVariable String ro_id) {
        userService.removeUser(ro_id);
        return R.OK();
    }

    @DeleteMapping( "")
    public R<?> execDel(@RequestBody String[] ids) {
        userService.removeUser(ids);
        return R.OK();
    }

    @GetMapping( "/fun")
    public R<List<Role>> allRoleList(){
        List<Role> menuVoList = userService.getRoleList();
        return R.OK(menuVoList);
    }

    @GetMapping( "/fun/{userId}")
    public R<List<Integer>> getUserRoleList(@PathVariable String userId){
        List<Integer> funIdList = userService.getUserRoleList(userId);
        return R.OK(funIdList);
    }

    @PutMapping( "/fun")
    public R<?> execUpdateUserRoleIds(@RequestBody Map<String,Object> map) {
        userService.updateUserRole(map);
        return R.OK();
    }



}
