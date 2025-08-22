package org.oaoa.demo.api.leave;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.LeaveStatusEnum;
import org.oaoa.demo.common.currentuser.CurrentUser;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;
import org.oaoa.demo.service.leave.ApprevService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/leave/apprev")
@RequiredArgsConstructor
public class ApprevController {
    private final ApprevService apprevService;

    private final CurrentUser currentUser;

    @GetMapping("")
    public R<PageVo<Leave>> apprevList(LeaveQueryDto dto){
        dto.setE_id(currentUser.getUserId());
        PageVo<Leave> result = apprevService.getApprevList(dto);
        return R.OK(result);
    }
    @PostMapping
    public R<?> execAdd(@RequestBody LeaveDto leaveDto) {
        leaveDto.setE_id(currentUser.getUserId());
        leaveDto.setL_status(LeaveStatusEnum.NOT_SUBMIT.getCode());
        leaveDto.setL_submit_date(new Date());
        apprevService.addLeave(leaveDto);
        return R.OK();
    }
    @PutMapping
    public R<?> execUpd(@RequestBody LeaveDto leaveDto) {
        leaveDto.setL_submit_date(new Date());
        apprevService.updateLeave(leaveDto);
        return R.OK();
    }

    @DeleteMapping( "/{id}")
    public R<?> execDel(@PathVariable Long id) {
        apprevService.removeLeave(id);
        return R.OK();
    }

    @DeleteMapping( "")
    public R<?> execDel(@RequestBody Long... ids) {
        apprevService.removeLeave(ids);
        return R.OK();
    }

    @PutMapping("/submit/{id}")
    public R<?> execSubmit(@PathVariable Long id){
        apprevService.submitLeave(id);
        return R.OK();
    }
    @PutMapping("/back/{id}")
    public R<?> execBack(@PathVariable Long id){
        apprevService.backLeave(id);
        return R.OK();
    }
}
