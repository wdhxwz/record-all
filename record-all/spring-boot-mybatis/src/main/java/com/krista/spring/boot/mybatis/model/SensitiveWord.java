package com.krista.spring.boot.mybatis.model;

import java.util.Date;

/**
 * SensitiveWord 敏感词数据类
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 14:57
 */
public class SensitiveWord {
    /**
     * 敏感词id
     */
    private Long id;
    /**
     * 敏感词内容
     */
    private String name;
    /**
     * 状态标识：0无效，1有效
     */
    private Integer status;
    /**
     * 后台创建人
     */
    private String createBackUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public SensitiveWord(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBackUserId() {
        return createBackUserId;
    }

    public void setCreateBackUserId(String createBackUserId) {
        this.createBackUserId = createBackUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
