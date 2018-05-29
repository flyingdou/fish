package com.fish.pojo;

import java.util.Date;

/**
 * 
 * @author dou
 * 提现账号(tb_cash_account)
 *
 */
public class CashAccount {
    private Integer id;

    /**
     * 用户
     */
    private Integer user;

    /**
     * 账号类型 (0支付宝   1银联)
     */
    private Integer type;

    /**
     * 支付平台
     */
    private String payPlatform;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform == null ? null : payPlatform.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}