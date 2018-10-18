package com.krista.java8.test.model;

import java.util.Date;

/**
 * @Author: dw_wanghonghong
 * @Date: 2018/10/17 10:19
 * @Description: 苹果
 */
public class Apple {
    /**
     * 品牌
     */
    private String brand;
    /**
     * 产地
     */
    private String placeOfProduction;
    /**
     * 颜色
     */
    private String color;
    /**
     * 重量,单位克
     */
    private Long weight;
    /**
     * 生产日期
     */
    private Date productionDate;
    /**
     * 保质期：天
     */
    private Integer shelfLife;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlaceOfProduction() {
        return placeOfProduction;
    }

    public void setPlaceOfProduction(String placeOfProduction) {
        this.placeOfProduction = placeOfProduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }
}
