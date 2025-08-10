package org.oaoa.demo.dto;

public class DepDto {
    private Integer d_id;// 部门编号
    private String d_name;// 部门名称
    private String d_remark;// 部门描述

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public String getD_remark() {
        return d_remark;
    }

    public void setD_remark(String d_remark) {
        this.d_remark = d_remark;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public Integer getD_status() {
        return d_status;
    }

    public void setD_status(Integer d_status) {
        this.d_status = d_status;
    }

    private Integer d_status;// 部门状态

}
