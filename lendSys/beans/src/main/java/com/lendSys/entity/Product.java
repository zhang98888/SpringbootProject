package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "rent_num")
    private Integer rentNum;

    @Column(name = "product_status")
    private Integer productStatus;

    @Column(name = "product_des")
    private String productDes;

    @Column(name = "product_level")
    private String productLevel;


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
     * @return category_id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    /**
     * @return rent_num
     */
    public Integer getRentNum() {
        return rentNum;
    }

    /**
     * @param rentNum
     */
    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    /**
     * @return product_status
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * @param productStatus
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * @return product_des
     */
    public String getProductDes() {
        return productDes;
    }

    /**
     * @param productDes
     */
    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    /**
     * @return product_level
     */
    public String getProductLevel() {
        return productLevel;
    }

    /**
     * @param productLevel
     */
    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }
}