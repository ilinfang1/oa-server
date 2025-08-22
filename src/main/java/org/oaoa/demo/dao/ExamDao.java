package org.oaoa.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;

import java.util.List;

@Mapper
public interface ExamDao {
    List<Leave> findExamList(LeaveQueryDto dto);

    @Update("update t_leave set l_sp_advice=#{l_sp_advice},l_spr=#{l_spr},l_sp_date=#{l_sp_date},l_status=#{l_status} where l_id = #{l_id}")
    void spLeave(LeaveDto leaveDto);
}
