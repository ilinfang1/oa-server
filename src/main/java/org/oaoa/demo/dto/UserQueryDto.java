package org.oaoa.demo.dto;

import lombok.Data;
import org.oaoa.demo.common.page.PageParamDto;

@Data
public class UserQueryDto extends PageParamDto {
    private String u_id;
    private String u_name;
}
