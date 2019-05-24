package com.krista.dubbo.user.center.api.bean;

/**
 * UserVo
 *
 * @author krista
 * @version V1.0
 * @since 2019/5/24 22:56
 */
public class UserVo {
    private Long id;
    private String name;
    private Integer sex;
    private Integer age;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
