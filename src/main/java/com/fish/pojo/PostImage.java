package com.fish.pojo;

import java.util.Date;

/**
 * 图片实体类(tb_post_image) 
 */
public class PostImage {
    private Integer id;

    // 帖子Id
    private Integer post;

    // 图片名称
    private String image;

    // 上传时间
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}