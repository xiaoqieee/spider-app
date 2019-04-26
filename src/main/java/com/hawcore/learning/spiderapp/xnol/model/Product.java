package com.hawcore.learning.spiderapp.xnol.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xn025665
 * @date Create on 2019/4/26 16:39
 */
public class Product {

    private Integer id;

    private Integer productId;

    private Long productEncryId;

    private String productName;

    private BigDecimal annualRate;

    private Integer productTerm;

    private BigDecimal productAmount;

    private BigDecimal creditAmount;

    private BigDecimal principalAmount;

    private BigDecimal transferProfit;

    private BigDecimal transferProfitRate;

    private Date publishTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getProductEncryId() {
        return productEncryId;
    }

    public void setProductEncryId(Long productEncryId) {
        this.productEncryId = productEncryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public Integer getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
        this.productTerm = productTerm;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getTransferProfit() {
        return transferProfit;
    }

    public void setTransferProfit(BigDecimal transferProfit) {
        this.transferProfit = transferProfit;
    }

    public BigDecimal getTransferProfitRate() {
        return transferProfitRate;
    }

    public void setTransferProfitRate(BigDecimal transferProfitRate) {
        this.transferProfitRate = transferProfitRate;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
