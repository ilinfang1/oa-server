package org.oaoa.demo.dto;

import lombok.Data;
import org.oaoa.demo.common.page.PageParamDto;

@Data
public class LeaveQueryDto extends PageParamDto {
    private String l_id;
    private String e_id;//员工编号
    private String l_cause;
    private Integer l_status;
    private String leader_id;//部门领导编号
    private String e_name;//员工名称
}
