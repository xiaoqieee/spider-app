package com.hawcore.learning.spiderapp.elm.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ElemeFood {

    @JSONField(name = "item_id")
    private String itemId;

    private String name;

    private Double rating;

    @JSONField(name = "rating_count")
    private Long ratingCount;

    private String description;

    private List<ElemeFoodSpec> specfoods;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ElemeFoodSpec> getSpecfoods() {
        return specfoods;
    }

    public void setSpecfoods(List<ElemeFoodSpec> specfoods) {
        this.specfoods = specfoods;
    }

    @Override
    public String toString() {
        return "ElemeFood [itemId=" + itemId + ", name=" + name + ", rating="
                + rating + ", ratingCount=" + ratingCount + ", description="
                + description + ", specfoods=" + specfoods + "]";
    }

}

