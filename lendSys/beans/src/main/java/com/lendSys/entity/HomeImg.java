package com.lendSys.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "home_img")
public class HomeImg {
    @Id
    @Column(name = "img_id")
    private Integer imgId;

    @Column(name = "img_url")
    private Integer imgUrl;

    @Column(name = "prod_id")
    private Integer prodId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "index_type")
    private Integer indexType;

    @Column(name = "img_bg_color")
    private String imgBgColor;

    private Integer status;

    private Integer level;

    /**
     * @return img_id
     */
    public Integer getImgId() {
        return imgId;
    }

    /**
     * @param imgId
     */
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    /**
     * @return img_url
     */
    public Integer getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return prod_id
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * @param prodId
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
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
     * @return index_type
     */
    public Integer getIndexType() {
        return indexType;
    }

    /**
     * @param indexType
     */
    public void setIndexType(Integer indexType) {
        this.indexType = indexType;
    }

    /**
     * @return img_bg_color
     */
    public String getImgBgColor() {
        return imgBgColor;
    }

    /**
     * @param imgBgColor
     */
    public void setImgBgColor(String imgBgColor) {
        this.imgBgColor = imgBgColor;
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

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}