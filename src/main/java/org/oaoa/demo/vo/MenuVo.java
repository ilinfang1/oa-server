package org.oaoa.demo.vo;

import lombok.Data;

import java.util.List;


@Data
public class MenuVo {
    private  Integer menuId;//菜单id
    private  String menuName;//菜单名称
    private  String menuUrl;//菜单地址

    private List<MenuVo> children;//子菜单
}
