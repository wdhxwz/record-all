package com.krista.mybatis.sample.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    @Id
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    private static final long serialVersionUID = 1L;

    /**
     * @return menu_id
     */
    public Long getMenuId() {
        return menuId;
    }

    public SysMenu withMenuId(Long menuId) {
        this.setMenuId(menuId);
        return this;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取父菜单ID，一级菜单为0
     *
     * @return parent_id - 父菜单ID，一级菜单为0
     */
    public Long getParentId() {
        return parentId;
    }

    public SysMenu withParentId(Long parentId) {
        this.setParentId(parentId);
        return this;
    }

    /**
     * 设置父菜单ID，一级菜单为0
     *
     * @param parentId 父菜单ID，一级菜单为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    public SysMenu withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单URL
     *
     * @return url - 菜单URL
     */
    public String getUrl() {
        return url;
    }

    public SysMenu withUrl(String url) {
        this.setUrl(url);
        return this;
    }

    /**
     * 设置菜单URL
     *
     * @param url 菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取授权(多个用逗号分隔，如：user:list,user:create)
     *
     * @return perms - 授权(多个用逗号分隔，如：user:list,user:create)
     */
    public String getPerms() {
        return perms;
    }

    public SysMenu withPerms(String perms) {
        this.setPerms(perms);
        return this;
    }

    /**
     * 设置授权(多个用逗号分隔，如：user:list,user:create)
     *
     * @param perms 授权(多个用逗号分隔，如：user:list,user:create)
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * 获取类型   0：目录   1：菜单   2：按钮
     *
     * @return type - 类型   0：目录   1：菜单   2：按钮
     */
    public Integer getType() {
        return type;
    }

    public SysMenu withType(Integer type) {
        this.setType(type);
        return this;
    }

    /**
     * 设置类型   0：目录   1：菜单   2：按钮
     *
     * @param type 类型   0：目录   1：菜单   2：按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取菜单图标
     *
     * @return icon - 菜单图标
     */
    public String getIcon() {
        return icon;
    }

    public SysMenu withIcon(String icon) {
        this.setIcon(icon);
        return this;
    }

    /**
     * 设置菜单图标
     *
     * @param icon 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    public SysMenu withOrderNum(Integer orderNum) {
        this.setOrderNum(orderNum);
        return this;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", perms=").append(perms);
        sb.append(", type=").append(type);
        sb.append(", icon=").append(icon);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}