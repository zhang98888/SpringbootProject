package com.lendSys.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Useraddress {
    @Id
    @Column(name = "addrId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addrid;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_mobile")
    private String receiverMobile;

    private String city;

    private String address;

    private String postcode;

    private String status;

    @Column(name = "default_address")
    private String defaultAddress;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return addrId
     */
    public Integer getAddrid() {
        return addrid;
    }

    /**
     * @param addrid
     */
    public void setAddrid(Integer addrid) {
        this.addrid = addrid;
    }

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
     * @return receiver_name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * @param receiverName
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * @return receiver_mobile
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * @param receiverMobile
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return default_address
     */
    public String getDefaultAddress() {
        return defaultAddress;
    }

    /**
     * @param defaultAddress
     */
    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}