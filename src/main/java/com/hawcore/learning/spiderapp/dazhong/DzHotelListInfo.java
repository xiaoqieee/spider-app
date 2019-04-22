package com.hawcore.learning.spiderapp.dazhong;


import java.math.BigDecimal;

public class DzHotelListInfo {
    private Integer id;

    private String name;

    private String region;

    private String position;

    private BigDecimal price;

    private Integer rank;

    private Integer comments;

    public DzHotelListInfo() {
    }

    public DzHotelListInfo(Integer id, String name, String region, String position, BigDecimal price, Integer rank, Integer comments) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.position = position;
        this.price = price;
        this.rank = rank;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "DzHotelListInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", position='" + position + '\'' +
                ", price=" + price +
                ", rank=" + rank +
                ", comments=" + comments +
                '}';
    }
}
