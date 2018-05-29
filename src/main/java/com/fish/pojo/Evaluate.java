package com.fish.pojo;

import java.util.Date;

/**
 * 
 * @author dou
 * 评论(tb_evaluate)
 *
 */
public class Evaluate {
    private Integer id;

    /**
     * 评论者
     */
    private Integer user;

    /**
     * 被评论的帖子
     */
    private Integer post;

    /**
     * 被回复的评论
     */
    private Integer parent;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date autoDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}