package org.oaoa.demo.dto;

import lombok.Data;
import org.oaoa.demo.common.page.PageParamDto;

@Data
public class DepQueryDto extends PageParamDto {
    private String d_id;//部门编号
    private String d_name;//部门名称
    private String d_remark;//部门描述
    private Integer d_status;//部门状态
}
