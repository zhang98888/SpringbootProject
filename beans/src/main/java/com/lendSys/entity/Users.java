package com.lendSys.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name = "userId")
    private Integer userid;

    @Column(name = "userName")
    private String username;

    @Column(name = "departmentId")
    private Integer departmentid;

    @Column(name = "userImg")
    private String userimg;

    @Column(name = "userPwd")
    private String userpwd;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_level")
    private String userLevel;

    @Column(name = "user_mobile")
    private String userMobile;

    @Column(name = "user_birth")
    private Date userBirth;

    @Column(name = "working_status")
    private String workingStatus;

    @Column(name = "user_regtime")
    private Date userRegtime;

    @Column(name = "user_modtime")
    private Date userModtime;

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return departmentId
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * @param departmentid
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * @return userImg
     */
    public String getUserimg() {
        return userimg;
    }

    /**
     * @param userimg
     */
    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    /**
     * @return userPwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * @return user_sex
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * @param userSex
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * @return user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return user_level
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * @return user_mobile
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * @return user_birth
     */
    public Date getUserBirth() {
        return userBirth;
    }

    /**
     * @param userBirth
     */
    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    /**
     * @return working_status
     */
    public String getWorkingStatus() {
        return workingStatus;
    }

    /**
     * @param workingStatus
     */
    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    /**
     * @return user_regtime
     */
    public Date getUserRegtime() {
        return userRegtime;
    }

    /**
     * @param userRegtime
     */
    public void setUserRegtime(Date userRegtime) {
        this.userRegtime = userRegtime;
    }

    /**
     * @return user_modtime
     */
    public Date getUserModtime() {
        return userModtime;
    }

    /**
     * @param userModtime
     */
    public void setUserModtime(Date userModtime) {
        this.userModtime = userModtime;
    }
}