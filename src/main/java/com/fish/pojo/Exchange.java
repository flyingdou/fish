package com.fish.pojo;

import java.util.Date;

/**
 * 
 * @author dou
 * 积分兑换申请(tb_exchange)
 *
 */
public class Exchange {
    private Integer id;

    /**
     * 商品id
     */
    private Integer product;

    /**
     * 兑换用户
     */
    private Integer user;

    /**
     * 申请状态(0审核中    1审核通过    2审核失败    3发货中 )
     */
    private Integer status;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人联系电话
     */
    private String mobilephone;
    
    /**
     * 发布时间
     */
    private Date autoDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

	public Date getAutoDate() {
		return autoDate;
	}

	public void setAutoDate(Date autoDate) {
		this.autoDate = autoDate;
	}
}