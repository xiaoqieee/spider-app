package com.hawcore.learning.spiderapp.xnol.pipeline;

import com.hawcore.learning.spiderapp.util.DelayThreadManager;
import com.hawcore.learning.spiderapp.util.RequestHelper;
import com.hawcore.learning.spiderapp.xnol.model.Product;
import com.hawcore.learning.spiderapp.xnol.processor.XnolProductDetailProcessor;
import com.hawcore.learning.spiderapp.xnol.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xn025665
 * @date Create on 2019/4/26 18:46
 */
@Component
public class XnolProductPipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(XnolProductPipeline.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private XnolProductDetailPipeline xnolProductDetailPipeline;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Product> productList = resultItems.get("productList");
        for (Product product : productList) {
            try {
                boolean r = productService.insertIfAbsent(product);
                if (r) {
                    getPrincipalAmount(product.getProductEncryId());
                }
            } catch (Exception e) {
                logger.error("插入异常", e);
            }
        }
    }


    private void getPrincipalAmount(Long productId) {
        DelayThreadManager.addTask(() -> {
            Spider.create(new XnolProductDetailProcessor()).addRequest(RequestHelper.getDetailRequest(productId)).addPipeline(xnolProductDetailPipeline).thread(1).run();
        }, 5, TimeUnit.SECONDS);
    }


}
