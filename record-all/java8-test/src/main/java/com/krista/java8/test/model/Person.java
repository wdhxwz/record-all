package com.krista.java8.test.model;

/**
 * Person
 *
 * @author dw_wangdonghong
 * @date 2018/10/18 9:33
 */
public class Person {
    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Integer age, Integer sex) {
        this.age = age;
        this.sex = sex;
    }

    public Person(Integer age, Integer sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
