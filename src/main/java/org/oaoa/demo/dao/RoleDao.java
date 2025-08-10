package org.oaoa.demo.dao;

import org.apache.ibatis.annotations.*;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.DepQueryDto;
import org.oaoa.demo.dto.RoleDto;
import org.oaoa.demo.dto.RoleQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.model.Role;
import org.oaoa.demo.vo.MenuVo;

import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> findRoleList(RoleQueryDto roleQueryDto);


    @Select("select ifnull(max(ro_id),0) from t_role")
    int findMaxId();

    @Insert("insert into t_role(ro_id,ro_name) values(#{ro_id},#{ro_name})")
    void insertRole(RoleDto roleDto);

    @Update("update t_role set ro_name=#{ro_name} where ro_id=#{ro_id}")
    void updateRole(RoleDto roleDto);

    void deleteRole(@Param("ids") Integer[] ids);

    List<MenuVo> findAllFunList();

    @Select("select f_id  from t_rf where ro_id=#{roleId}")
    List<Integer> findRoleFunIdList(Integer roleId);
}
