package com.hawcore.learning.spiderapp.xnol.service;

import com.hawcore.learning.spiderapp.xnol.mapper.ProductMapper;
import com.hawcore.learning.spiderapp.xnol.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xn025665
 * @date Create on 2019/4/26 16:50
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public void insert(Product product) {
        productMapper.insert(product);
    }

    public Product findByProductId(Integer productId) {
        return productMapper.findByProductId(productId);
    }

    public void updatePrincipalAmount(Integer productId, BigDecimal principalAmount) {
        productMapper.updatePrincipalAmount(productId, principalAmount);
    }

    public boolean insertIfAbsent(Product product) {
        Product p = findByProductId(product.getProductId());
        if (p == null) {
            insert(product);
            return true;
        }
        return false;
    }
}
