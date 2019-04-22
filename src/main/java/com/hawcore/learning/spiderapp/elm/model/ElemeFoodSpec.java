package com.hawcore.learning.spiderapp.elm.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;

public class ElemeFoodSpec {

    private String name;

    @JSONField(name = "food_id")
    private String foodId;

    @JSONField(name = "item_id")
    private String itemId;

    @JSONField(name = "original_price")
    private BigDecimal originalPrice;

    @JSONField(name = "packing_fee")
    private BigDecimal packingFee;

    private BigDecimal price;

    @JSONField(name = "sku_id")
    private String skuId;

    @JSONField(name = "restaurant_id")
    private String restaurantId;

    private List<Spec> specs; // 规格名称

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(BigDecimal packingFee) {
        this.packingFee = packingFee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    public String getSpceName() {
        if(this.specs != null && this.specs.size() > 0) {
            return this.specs.get(0).getValue();
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ElemeFoodSpec [name=" + name + ", foodId=" + foodId
                + ", itemId=" + itemId + ", originalPrice=" + originalPrice
                + ", packingFee=" + packingFee + ", price=" + price + ", skuId="
                + skuId + ", restaurantId=" + restaurantId + ", specs=" + specs
                + "]";
    }

    /**
     * 规格
     *
     * @author Zhouluning
     *
     */
    public static class Spec {
        private String value; // 规格名称

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Spec [value=" + value + "]";
        }
    }
}

