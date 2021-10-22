package com.lendSys.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_login_his")
public class UserLoginHis {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "AREA")
    private String area;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "IP")
    private String ip;

    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return AREA
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return COUNTRY
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return USER_ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return LOGIN_TIME
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}