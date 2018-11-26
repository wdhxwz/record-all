package com.krista.spring.boot.mybatis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ip
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/26 16:25
 */
@Table(name = "tb_ip")
public class Ip {
    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 起始ip
     */
    private String fromIp;
    /**
     * 终止ip
     */
    private String toIp;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份/直辖市
     */
    private String province;
    /**
     * 地级市
     */
    private String city;
    /**
     * 区县
     */
    private String county;
    /**
     * 行政区代码
     */
    private String zoneCode;
    /**
     * 覆盖范围(km)
     */
    @Column(name = "`range`")
    private Double range;
    /**
     * 中心点经度
     */
    private Double centerLongitude;
    /**
     * 中心点维度
     */
    private Double centerLatitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getCenterLongitude() {
        return centerLongitude;
    }

    public void setCenterLongitude(Double centerLongitude) {
        this.centerLongitude = centerLongitude;
    }

    public Double getCenterLatitude() {
        return centerLatitude;
    }

    public void setCenterLatitude(Double centerLatitude) {
        this.centerLatitude = centerLatitude;
    }

    @Override
    public String toString() {
        return "Ip{" +
                "id=" + id +
                ", fromIp='" + fromIp + '\'' +
                ", toIp='" + toIp + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", zoneCode='" + zoneCode + '\'' +
                ", range=" + range +
                ", centerLongitude=" + centerLongitude +
                ", centerLatitude=" + centerLatitude +
                '}';
    }
}
