package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author dou
 * 垂钓日志(tb_fishing_logs)
 *
 */
public class FishingLogs {
    private Integer id;

    /**
     * 帖子
     */
    private Integer post;

    /**
     * 签到
     */
    private Integer sign;

    /**
     * 渔获数量
     */
    private Integer fishingCount;

    /**
     * 渔获重量
     */
    private BigDecimal fishingWeight;

    /**
     * 单尾最重
     */
    private BigDecimal singleMaxWeight;

    /**
     * 饵料
     */
    private String food;

    /**
     * 钓竿长度
     */
    private BigDecimal rodLength;

    /**
     * 线组
     */
    private String lineGroup;

    /**
     * 鱼漂
     */
    private String floatEyes;
    
    /**
     * 钩
     */
    private Integer hookSize;

    /**
     * 调钓
     */
    private String method;

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

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getFishingCount() {
        return fishingCount;
    }

    public void setFishingCount(Integer fishingCount) {
        this.fishingCount = fishingCount;
    }

    public BigDecimal getFishingWeight() {
        return fishingWeight;
    }

    public void setFishingWeight(BigDecimal fishingWeight) {
        this.fishingWeight = fishingWeight;
    }

    public BigDecimal getSingleMaxWeight() {
        return singleMaxWeight;
    }

    public void setSingleMaxWeight(BigDecimal singleMaxWeight) {
        this.singleMaxWeight = singleMaxWeight;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food == null ? null : food.trim();
    }

    public BigDecimal getRodLength() {
        return rodLength;
    }

    public void setRodLength(BigDecimal rodLength) {
        this.rodLength = rodLength;
    }

    public String getLineGroup() {
        return lineGroup;
    }

    public void setLineGroup(String lineGroup) {
        this.lineGroup = lineGroup == null ? null : lineGroup.trim();
    }

    public String getFloatEyes() {
        return floatEyes;
    }

    public void setFloatEyes(String floatEyes) {
        this.floatEyes = floatEyes == null ? null : floatEyes.trim();
    }

    public Integer getHookSize() {
        return hookSize;
    }

    public void setHookSize(Integer hookSize) {
        this.hookSize = hookSize;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }
}