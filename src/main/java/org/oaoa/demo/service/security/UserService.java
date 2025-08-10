package org.oaoa.demo.service.security;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.UserDto;
import org.oaoa.demo.dto.UserQueryDto;
import org.oaoa.demo.model.Role;
import org.oaoa.demo.model.User;
import org.oaoa.demo.vo.MenuVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    PageVo<User> getUserPage(UserQueryDto userQueryDto);

    void addUser(UserDto userDto);

    void updateUser(UserDto userDto);


    void removeUser(String... ids);

    List<Role> getRoleList();

    List<Integer> getUserRoleList(String userId);

    void updateUserRole(Map<String,Object> map);
}
