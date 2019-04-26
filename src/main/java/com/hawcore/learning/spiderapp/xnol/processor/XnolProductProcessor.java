package com.hawcore.learning.spiderapp.xnol.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.hawcore.learning.spiderapp.dazhong.DzHotelListInfo;
import com.hawcore.learning.spiderapp.util.CookieUtils;
import com.hawcore.learning.spiderapp.util.DelayThreadManager;
import com.hawcore.learning.spiderapp.util.RequestHelper;
import com.hawcore.learning.spiderapp.xnol.model.Product;
import com.hawcore.learning.spiderapp.xnol.pipeline.XnolProductDetailPipeline;
import org.apache.rocketmq.common.protocol.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class XnolProductProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("xiaoniu88.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Mobile Safari/53");


    @Override
    public void process(Page page) {
        JSONObject joData = JSON.parseObject(page.getRawText());
        JSONArray products = (JSONArray) JSONPath.eval(joData, "$.data.data");
        List<Product> productList = new ArrayList<>();
        for (Object pro : products) {
            JSONObject p = (JSONObject) pro;
            Product product = new Product();
            product.setProductId(((Integer) JSONPath.eval(p, "$.productId")));
            product.setAnnualRate(((BigDecimal) JSONPath.eval(p, "$.annualRate")).divide(BigDecimal.valueOf(100)));
            product.setProductTerm(((Integer) JSONPath.eval(p, "$.productTerm")));
            product.setCreateTime(new Date());
            BigDecimal productAmount = ((BigDecimal) JSONPath.eval(p, "$.productAmount"));
            BigDecimal transferAmount = ((BigDecimal) JSONPath.eval(p, "$.tsfProfitAmount"));
            transferAmount = transferAmount != null ? transferAmount : BigDecimal.valueOf(0);
            product.setPrincipalAmount(productAmount);
            product.setProductAmount(productAmount);
            product.setCreditAmount(productAmount.add(transferAmount));
            product.setTransferProfit(transferAmount);
            product.setProductEncryId((Long) JSONPath.eval(p, "$.productIdEncry"));
            product.setProductName((String) JSONPath.eval(p, "$.productName"));
            product.setPublishTime(new Date((Long) JSONPath.eval(p, "$.publishTime")));
            product.setTransferProfitRate((BigDecimal) JSONPath.eval(p, "$.tsfProfitAmountRatio"));
            productList.add(product);
        }
        page.putField("productList", productList);
    }


    @Override
    public Site getSite() {
        return site;
    }
}
