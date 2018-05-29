package com.fish.pojo;

import java.util.Date;
/**
 * 用户实体类(tb_user) 
 */
public class User {
	private Integer id;

	// 昵称
	private String nick;

	// 头像
	private String image;

	// 性别
	private String sex;

	// 注册日期
	private Date registerDate;

	// 登录日期
	private Date loginDate;

	// 省
	private String province;

	// 市
	private String city;

	// 区
	private String county;

	// 积分
	private Integer score;

	// 类型
	private String type;

	// 首次垂钓日期
	private Date fristFishingDate;

	// 收徒费用
	private Integer techFee;

	// 手机号
	private String telephone;

	// 微信标识(openId ,unionId)
	private String wechatId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick == null ? null : nick.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county == null ? null : county.trim();
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Date getFristFishingDate() {
		return fristFishingDate;
	}

	public void setFristFishingDate(Date fristFishingDate) {
		this.fristFishingDate = fristFishingDate;
	}

	public Integer getTechFee() {
		return techFee;
	}

	public void setTechFee(Integer techFee) {
		this.techFee = techFee;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId == null ? null : wechatId.trim();
	}
}