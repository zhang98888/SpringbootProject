package com.lendSys.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product_picture")
public class ProductPicture {
    @Id
    @Column(name = "picture_id")
    private Integer pictureId;

    @Column(name = "product_id")
    private Integer productId;

    private String url;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "is_main")
    private Integer isMain;

    /**
     * @return picture_id
     */
    public Integer getPictureId() {
        return pictureId;
    }

    /**
     * @param pictureId
     */
    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return is_main
     */
    public Integer getIsMain() {
        return isMain;
    }

    /**
     * @param isMain
     */
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }
}