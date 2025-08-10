package org.oaoa.demo.dao;

import org.apache.ibatis.annotations.*;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.DepQueryDto;
import org.oaoa.demo.model.Dep;

import java.util.List;

@Mapper
public interface DepDao {

    List<Dep> findDepList(DepQueryDto depQueryDto);


    @Select("select ifnull(max(d_id),0) from t_dep")
    int findMaxId();

    @Insert("insert into t_dep(d_id,d_name,d_status,d_remark) values(#{d_id},#{d_name},#{d_status},#{d_remark})")
    void insertDep(DepDto depDto);

    @Update("update t_dep set d_name=#{d_name},d_remark=#{d_remark} where d_id=#{d_id}")
    void updateDep(DepDto depDto);

    void deleteDep(@Param("dIds") Integer[] dIds);

    boolean findExistsUndetermined(@Param("dIds") Integer[] dIds);

    @Update("update t_dep set d_status=#{status} where d_id=#{id}")
    void changeStatus(@Param("id") Integer id, @Param("status") Integer status);
}
