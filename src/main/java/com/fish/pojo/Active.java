package com.fish.pojo;

import java.util.Date;

/**
 * 活动实体类(tb_active)
 */
public class Active {
    private Integer id;

    // 海报
    private String poster;

    // 名称
    private String name;

    // 开始时间
    private Date startTime;

    // 结束时间
    private Date endTime;

    // 地址
    private String address;

    
    // 联系电话
    private String telephone;

    // 活动所在城市
    private String city;

    // 发起人Id
    private Integer creator;

    // 人数上限
    private Integer upperLimit;

    // 费用
    private Integer price;

    // 说明
    private String remark;

    // 关联钓场
    private Integer fishingGround;

    // 发布时间
    private Date autoDate;
    
    // 用户开关活动(0: 关闭, 1: 开启)
    private Integer isOpen;
    
    // 审核状态(0: 未审核, 1: 已审核)
    private Integer status;
    
    // 热门活动推荐(0: 未推荐, 1: 已推荐)
    private Integer recommend;
    
    // 推荐日期
    private Date recommendDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster == null ? null : poster.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getFishingGround() {
        return fishingGround;
    }

    public void setFishingGround(Integer fishingGround) {
        this.fishingGround = fishingGround;
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Date getRecommendDate() {
		return recommendDate;
	}

	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}
}