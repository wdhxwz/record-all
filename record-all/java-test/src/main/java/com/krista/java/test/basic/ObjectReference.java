package com.krista.java.test.basic;

import java.util.Date;

/**
 * ObjectReference 对象引用问题
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/18 10:01
 */
public class ObjectReference {
    public static void main(String[] args) {
        Test3 obj = new Test3();
        Date now = new Date();
        Integer age = 1888;
        obj.setAge(age);

        obj.setRegDate(now);

        // 问题所在！
        // now是个可变的引用对象，修改了之后，所有引用到的都会被修改到
        now.setYear(4000);
        System.out.println(obj.getAge());
        age = 21;
        System.out.println(obj.getAge());
        System.out.println(obj.getRegDate());
        System.out.println(obj.getAge());
    }

    public static class Test3 {

        private Date regDate;
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public void setRegDate(Date regDate) {
            this.regDate = (Date) regDate.clone();
        }

        public Date getRegDate() {
            return regDate;
        }
    }
}


