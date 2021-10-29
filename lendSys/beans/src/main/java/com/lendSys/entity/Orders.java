package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Orders {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "order_remark")
    private String orderRemark;

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
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * @param orderRemark
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }
}