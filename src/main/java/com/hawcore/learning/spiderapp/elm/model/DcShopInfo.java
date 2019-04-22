package com.hawcore.learning.spiderapp.elm.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class DcShopInfo {
    /**
     *
     * DC_SHOP_INFO.ID
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 任务ID
     * DC_SHOP_INFO.TASK_ID
     *
     * @mbg.generated
     */
    private Long taskId;

    /**
     * 数据渠道
     1:饿了么
     2:美团
     * DC_SHOP_INFO.CHANNEL
     *
     * @mbg.generated
     */
    private Integer channel;

    /**
     * 店铺名称
     * DC_SHOP_INFO.NAME
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 店铺地址
     * DC_SHOP_INFO.ADDRESS
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 店铺坐标-纬度
     * DC_SHOP_INFO.LATITUDE
     *
     * @mbg.generated
     */
    private String latitude;

    /**
     * 店铺坐标-经度
     * DC_SHOP_INFO.LONGITUDE
     *
     * @mbg.generated
     */
    private String longitude;

    /**
     * 联系电话
     * DC_SHOP_INFO.PHONE
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * 评分
     * DC_SHOP_INFO.RATING
     *
     * @mbg.generated
     */
    private Double rating;

    /**
     * 评价数
     * DC_SHOP_INFO.RATING_COUNT
     *
     * @mbg.generated
     */
    private Integer ratingCount;

    /**
     * 渠道店铺ID
     * DC_SHOP_INFO.CHANNEL_SHOP_ID
     *
     * @mbg.generated
     */
    private String channelShopId;

    /**
     * 创建日期
     * DC_SHOP_INFO.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @mbg.generated
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     *
     * @mbg.generated
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     *
     * @mbg.generated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *
     * @mbg.generated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @mbg.generated
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @mbg.generated
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @mbg.generated
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @mbg.generated
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @mbg.generated
     */
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @mbg.generated
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     *
     * @mbg.generated
     */
    public Integer getRatingCount() {
        return ratingCount;
    }

    /**
     *
     * @mbg.generated
     */
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    /**
     *
     * @mbg.generated
     */
    public String getChannelShopId() {
        return channelShopId;
    }

    /**
     *
     * @mbg.generated
     */
    public void setChannelShopId(String channelShopId) {
        this.channelShopId = channelShopId;
    }

    /**
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

