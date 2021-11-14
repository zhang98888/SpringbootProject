package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Orders {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "order_remark")
    private Integer orderRemark;

    @Column(name = "good_id")
    private Integer goodId;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


    @Column(name = "length")
    private Integer length;


    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return user_id
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
     * @return order_status
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return order_remark
     */
    public Integer getOrderRemark() {
        return orderRemark;
    }

    /**
     * @param orderRemark
     */
    public void setOrderRemark(Integer orderRemark) {
        this.orderRemark = orderRemark;
    }

    /**
     * @return good_id
     */
    public Integer getGoodId() {
        return goodId;
    }

    /**
     * @param goodId
     */
    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    /**
     * @return orderNum
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * @return length
     */
    public Integer getLength() {
        return length;
    }
    /**
     * @param length
     */
    public void setLength(Integer length) {
        this.length = length;
    }
}