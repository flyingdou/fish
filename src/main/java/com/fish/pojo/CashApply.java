package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author dou
 * 提现申请(tb_cash_apply)
 *
 */
public class CashApply {
    private Integer id;

    /**
     * 提现用户
     */
    private Integer user;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 提现账号
     */
    private String account;

    /**
     * 提现状态
     */
    private Integer status;

    /**
     * 提现时间
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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