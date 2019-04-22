package com.hawcore.learning.spiderapp.elm.model;


import com.alibaba.fastjson.annotation.JSONField;

public class ElemeShop {

    private String id;

    private String name;

    private String address;

    private String latitude;

    private String longitude;

    private String phone;

    private Double rating;

    @JSONField(name = "rating_count")
    private Integer ratingCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return "ElemeShop [id=" + id + ", name=" + name + ", address=" + address
                + ", latitude=" + latitude + ", longitude=" + longitude
                + ", phone=" + phone + ", rating=" + rating + ", ratingCount="
                + ratingCount + "]";
    }

}

