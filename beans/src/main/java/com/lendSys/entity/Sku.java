package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Sku {
    @Id
    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "sku_img")
    private String skuImg;

    @Column(name = "rent_level")
    private Integer rentLevel;

    private Integer stock;

    private Integer status;

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
     * @return sku_name
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * @param skuName
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * @return sku_img
     */
    public String getSkuImg() {
        return skuImg;
    }

    /**
     * @param skuImg
     */
    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    /**
     * @return rent_level
     */
    public Integer getRentLevel() {
        return rentLevel;
    }

    /**
     * @param rentLevel
     */
    public void setRentLevel(Integer rentLevel) {
        this.rentLevel = rentLevel;
    }

    /**
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}