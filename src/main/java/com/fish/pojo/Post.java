package com.fish.pojo;

import java.util.Date;

/**
 * 帖子实体类(tb_post)
 */
public class Post {
    private Integer id;

    // 发帖人Id
    private Integer user;

    // 内容
    private String content;

    // 视频
    private String videoUrl;

    // 钓法
    private String fishingMethod;

    // 渔获
    private String fishingGrain;

    // 发帖时间
    private Date posterDate;

    // 垂钓日志Id
    private Integer fishingLogs;

    // 打赏金额
    private Integer rewardAmount;
    
    // 经度
    private Double longitude;
    
    // 纬度
    private Double latitude;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getFishingMethod() {
        return fishingMethod;
    }

    public void setFishingMethod(String fishingMethod) {
        this.fishingMethod = fishingMethod == null ? null : fishingMethod.trim();
    }

    public String getFishingGrain() {
        return fishingGrain;
    }

    public void setFishingGrain(String fishingGrain) {
        this.fishingGrain = fishingGrain == null ? null : fishingGrain.trim();
    }

    public Date getPosterDate() {
        return posterDate;
    }

    public void setPosterDate(Date posterDate) {
        this.posterDate = posterDate;
    }

    public Integer getFishingLogs() {
        return fishingLogs;
    }

    public void setFishingLogs(Integer fishingLogs) {
        this.fishingLogs = fishingLogs;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}