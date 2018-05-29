package com.fish.pojo;

import java.util.Date;

/**
 * 
 * @author dou
 * 积分兑换商品(tb_score_product)
 *
 */
public class ScoreProduct {
    private Integer id;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 剩余库存
     */
    private Integer count;

    /**
     * 所需积分
     */
    private Integer score;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品状态(0未上架    1已上架)
     */
    private Integer status;

    /**
     * 操作时间
     */
    private Date autoDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}