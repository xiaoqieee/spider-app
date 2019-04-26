package com.hawcore.learning.spiderapp.xnol.mapper;

import com.hawcore.learning.spiderapp.xnol.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author xn025665
 * @date Create on 2019/4/26 16:37
 */
@Component
public interface ProductMapper {

    @Insert({
            "insert into t_product (id, productId, ",
            "productEncryId, productName, annualRate, ",
            "productTerm, productAmount, ",
            "creditAmount, principalAmount, transferProfit, ",
            "transferProfitRate, publishTime, createTime)",
            "values (#{id,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
            "#{productEncryId,jdbcType=BIGINT}, #{productName,jdbcType=VARCHAR}, #{annualRate,jdbcType=DECIMAL}, ",
            "#{productTerm,jdbcType=INTEGER}, #{productAmount,jdbcType=DECIMAL}, ",
            "#{creditAmount,jdbcType=DECIMAL}, #{principalAmount,jdbcType=DECIMAL}, #{transferProfit,jdbcType=DECIMAL}, ",
            "#{transferProfitRate,jdbcType=DECIMAL}, #{publishTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    int insert(Product record);

    @Select({
            "select",
            "id, productId, productEncryId, productName, annualRate, productTerm, productAmount, creditAmount, principalAmount, transferProfit,transferProfitRate, publishTime, createTime ",
            "from t_product",
            "where productId = #{productId,jdbcType=INTEGER}"
    })
    Product findByProductId(Integer productId);
}
