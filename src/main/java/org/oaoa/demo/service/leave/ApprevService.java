package org.oaoa.demo.service.leave;


import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;

public interface ApprevService {
    PageVo<Leave> getApprevList(LeaveQueryDto dto);
    void addLeave(LeaveDto leaveDto);
    void updateLeave(LeaveDto leaveDto);
    void removeLeave(Long... ids);
    void submitLeave(Long id);
    void backLeave(Long id);

}
