package com.krista.mybatis.sample.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dict")
public class SysDict implements Serializable {
    @Id
    private Long id;

    /**
     * 父id: 0表示顶级
     */
    private Long pid;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子配置项计数
     */
    @Column(name = "child_count")
    private Integer childCount;

    /**
     * 序号
     */
    private Integer seq;

    /**
     * 是否开启
     */
    @Column(name = "is_enabled")
    private Integer isEnabled;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    public SysDict withId(Long id) {
        this.setId(id);
        return this;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父id: 0表示顶级
     *
     * @return pid - 父id: 0表示顶级
     */
    public Long getPid() {
        return pid;
    }

    public SysDict withPid(Long pid) {
        this.setPid(pid);
        return this;
    }

    /**
     * 设置父id: 0表示顶级
     *
     * @param pid 父id: 0表示顶级
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    public SysDict withCode(String code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    public SysDict withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    public SysDict withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取子配置项计数
     *
     * @return child_count - 子配置项计数
     */
    public Integer getChildCount() {
        return childCount;
    }

    public SysDict withChildCount(Integer childCount) {
        this.setChildCount(childCount);
        return this;
    }

    /**
     * 设置子配置项计数
     *
     * @param childCount 子配置项计数
     */
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * 获取序号
     *
     * @return seq - 序号
     */
    public Integer getSeq() {
        return seq;
    }

    public SysDict withSeq(Integer seq) {
        this.setSeq(seq);
        return this;
    }

    /**
     * 设置序号
     *
     * @param seq 序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取是否开启
     *
     * @return is_enabled - 是否开启
     */
    public Integer getIsEnabled() {
        return isEnabled;
    }

    public SysDict withIsEnabled(Integer isEnabled) {
        this.setIsEnabled(isEnabled);
        return this;
    }

    /**
     * 设置是否开启
     *
     * @param isEnabled 是否开启
     */
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public SysDict withIsDeleted(Integer isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建者id
     *
     * @return create_user_id - 创建者id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    public SysDict withCreateUserId(Long createUserId) {
        this.setCreateUserId(createUserId);
        return this;
    }

    /**
     * 设置创建者id
     *
     * @param createUserId 创建者id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public SysDict withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", childCount=").append(childCount);
        sb.append(", seq=").append(seq);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}