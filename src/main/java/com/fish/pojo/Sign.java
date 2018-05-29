package com.fish.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 签到实体类(tb_sign) 
 */
public class Sign {
    private Integer id;

    // 时间
    private Date signDate;

    // 打卡者Id
    private Integer user;

    // 打卡地址
    private String address;

    // 经度
    private BigDecimal longitude;

    // 纬度
    private BigDecimal latitude;

    // 天气状态
    private String weatherStatus;

    // 温度
    private String temperature;

    // 气压
    private String pressure;

    // 风向
    private String windDirection;
    
    // 打卡钓场
    private Integer fishingGround;

    // 开始时间
    private Date fishingStartTime;

    // 结束时间
    private Date fishingEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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

	public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus == null ? null : weatherStatus.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection == null ? null : windDirection.trim();
    }

    public Integer getFishingGround() {
        return fishingGround;
    }

    public void setFishingGround(Integer fishingGround) {
        this.fishingGround = fishingGround;
    }
    
	public Date getFishingStartTime() {
		return fishingStartTime;
	}

	public void setFishingStartTime(Date fishingStartTime) {
		this.fishingStartTime = fishingStartTime;
	}

	public Date getFishingEndTime() {
		return fishingEndTime;
	}

	public void setFishingEndTime(Date fishingEndTime) {
		this.fishingEndTime = fishingEndTime;
	}
}