package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 钓场实体类(tb_fishing_ground)
 */
public class FishingGround {
	private Integer id;

	// 图片
	private String poster;

	// 名称
	private String name;

	// 类型 类型 (取tb_parameter表code字段为fishing_factory_c的值)
	private String type;

	// 收费方式 类型 (取tb_parameter表code字段为fishing_cost_c的值)
	private Integer feeType;

	// 费用
	private Integer fee;

	// 水域面积
	private Integer waterArea;

	// 水深
	private Integer waterDeep;

	// 地址
	private String address;

	// 经度
	private BigDecimal longitude;

	// 纬度
	private BigDecimal latitude;

	// 联系电话
	private String telephone;

	// 说明
	private String remark;

	// 所有者Id
	private Integer owner;

	// 发起时间
	private Date autoDate;

	// 发起者Id
	private Integer creator;

	// 所在城市
	private String city;

	// 微信门店poi_id
	private String poi_id;

	// 微信审核状态
	private Integer wechat_audit;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getWaterArea() {
		return waterArea;
	}

	public void setWaterArea(Integer waterArea) {
		this.waterArea = waterArea;
	}

	public Integer getWaterDeep() {
		return waterDeep;
	}

	public void setWaterDeep(Integer waterDeep) {
		this.waterDeep = waterDeep;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public Date getAutoDate() {
		return autoDate;
	}

	public void setAutoDate(Date autoDate) {
		this.autoDate = autoDate;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public Integer getWechat_audit() {
		return wechat_audit;
	}

	public void setWechat_audit(Integer wechat_audit) {
		this.wechat_audit = wechat_audit;
	}

}