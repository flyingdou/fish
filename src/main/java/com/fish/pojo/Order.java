package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author dou
 * 订单(tb_order)
 *
 */
public class Order {
    private Integer id;

    /**
     * 订单编号
     */
    private String no;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 商品类型(A拜师    B参加活动    C打赏     D结算   E提现 F卡券)
     */
    private String productType;

    /**
     * 商品Id(对应类型的主键)
     */
    private Integer productId;

    /**
     * 订单状态(0未付款    1已付款    2已结算     3结算金额分配      4提现支出)
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 付款者
     */
    private Integer fromUser;

    /**
     * 收款者
     */
    private Integer toUser;

    /**
     * 订单时间
     */
    private Date autoDate;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getToUser() {
        return toUser;
    }

    public void setToUser(Integer toUser) {
        this.toUser = toUser;
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}