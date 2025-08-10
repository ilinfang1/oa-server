package org.oaoa.demo.service.security;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.RoleDto;
import org.oaoa.demo.dto.RoleQueryDto;
import org.oaoa.demo.model.Role;
import org.oaoa.demo.vo.MenuVo;

import java.util.List;

public interface RoleService {
    PageVo<Role> getRolePage(RoleQueryDto roleQueryDto);

    void addRole(RoleDto roleDto);

    void updateRole(RoleDto roleDto);


    void removeRole(Integer... ids);

    List<MenuVo> getFunList();

    List<Integer> getRoleFunList(Integer roleId);
}
