package org.oaoa.demo.service.leave;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;



public interface ExamService {
    PageVo<Leave> getExamPage(LeaveQueryDto dto);

    void  spLeave(LeaveDto leaveDto);
}
