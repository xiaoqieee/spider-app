package com.hawcore.learning.spiderapp.xnol.pipeline;

import com.alibaba.fastjson.JSONPath;
import com.hawcore.learning.spiderapp.xnol.model.Product;
import com.hawcore.learning.spiderapp.xnol.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xn025665
 * @date Create on 2019/4/26 18:46
 */
@Component
public class XnolProductDetailPipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(XnolProductDetailPipeline.class);

    @Autowired
    private ProductService productService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        BigDecimal principalAmount = resultItems.get("principalAmount");
        Integer productId = resultItems.get("productId");
        productService.updatePrincipalAmount(productId, principalAmount);
    }


}
