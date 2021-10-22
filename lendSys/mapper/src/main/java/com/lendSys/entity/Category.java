package com.lendSys.entity;

import javax.persistence.*;

public class Category {
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private Integer categoryLevel;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "category_img")
    private String categoryImg;

    @Column(name = "category_bg_color")
    private String categoryBgColor;

    @Column(name = "category_picture")
    private String categoryPicture;

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
     * @return category_name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return category_level
     */
    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * @param categoryLevel
     */
    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * @return parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return category_img
     */
    public String getCategoryImg() {
        return categoryImg;
    }

    /**
     * @param categoryImg
     */
    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    /**
     * @return category_bg_color
     */
    public String getCategoryBgColor() {
        return categoryBgColor;
    }

    /**
     * @param categoryBgColor
     */
    public void setCategoryBgColor(String categoryBgColor) {
        this.categoryBgColor = categoryBgColor;
    }

    /**
     * @return category_picture
     */
    public String getCategoryPicture() {
        return categoryPicture;
    }

    /**
     * @param categoryPicture
     */
    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }
}