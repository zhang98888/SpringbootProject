package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Cart {
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "productId")
    private Integer productid;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "cart_num")
    private Integer cartNum;

    @Column(name = "cart_time")
    private Date cartTime;

    /**
     * @return cart_id
     */
    public Integer getCartId() {
        return cartId;
    }

    /**
     * @param cartId
     */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /**
     * @return productId
     */
    public Integer getProductid() {
        return productid;
    }

    /**
     * @param productid
     */
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
     * @return cart_time
     */
    public Date getCartTime() {
        return cartTime;
    }

    /**
     * @param cartTime
     */
    public void setCartTime(Date cartTime) {
        this.cartTime = cartTime;
    }
}