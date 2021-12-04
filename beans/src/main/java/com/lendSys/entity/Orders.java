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
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "order_remark")
    private Integer orderRemark;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "cart_num")
    private Integer cartNum;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


    @Column(name = "length")
    private Integer length;

    @Column(name = "product_name")
    private String productName;



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
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return cart_num
     */
    public Integer getCartNum() {
        return cartNum;
    }

    /**
     * @param cartNum
     */
    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }

    /**
     * @return start_date
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
     * @return end_date
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

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}