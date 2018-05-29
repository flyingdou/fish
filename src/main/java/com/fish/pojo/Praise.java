package com.fish.pojo;

import java.util.Date;

/**
 * 
 * @author dou
 * 点赞(tb_praise)
 *
 */
public class Praise {
    private Integer id;

    /**
     * 帖子
     */
    private Integer post;

    /**
     * 点赞者
     */
    private Integer user;

    /**
     * 是否有效(0无效   1有效)
     */
    private Integer status;

    /**
     * 点赞时间
     */
    private Date autoDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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