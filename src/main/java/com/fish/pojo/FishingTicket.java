package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author dou 垂钓券实体类
 */
public class FishingTicket {
	private Integer id;

	/**
	 * 封面
	 */
	private String poster;

	/**
	 * 垂钓券名称
	 */
	private String name;

	/**
	 * 有效期
	 */
	private Integer period;

	/**
	 * 适用钓场
	 */
	private String fitFishGround;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 发布人
	 */
	private Integer creator;

	/**
	 * 规则、说明
	 */
	private String remark;

	/**
	 * 用户开关(0关闭 1开启)
	 */
	private Integer isOpen;

	/**
	 * 审核状态(0 待审核 1审核通过)
	 */
	private Integer status;

	/**
	 * 发布时间
	 */
	private Date auto_date;

	/**
	 * 微信卡券ID
	 */
	private String cardId;

	/**
	 * setter && getter
	 */
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

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getFitFishGround() {
		return fitFishGround;
	}

	public void setFitFishGround(String fitFishGround) {
		this.fitFishGround = fitFishGround == null ? null : fitFishGround.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public Date getAuto_date() {
		return auto_date;
	}

	public void setAuto_date(Date auto_date) {
		this.auto_date = auto_date;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
