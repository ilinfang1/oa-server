package org.oaoa.demo.model;

import lombok.Data;

@Data
public class Dep {
    private Integer d_id;//部门编号
    private String d_name;//部门名称
    private String d_remark;//部门描述
    private Integer d_status;//部门状态
}
