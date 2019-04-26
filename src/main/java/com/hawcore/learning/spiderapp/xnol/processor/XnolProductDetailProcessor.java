package com.hawcore.learning.spiderapp.xnol.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.hawcore.learning.spiderapp.util.CookieUtils;
import com.hawcore.learning.spiderapp.xnol.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class XnolProductDetailProcessor implements PageProcessor {


    private Site site = Site.me()
            .setDomain("xiaoniu88.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Mobile Safari/53");

    @Override
    public void process(Page page) {
        JSONObject joData = JSON.parseObject(page.getRawText());
        JSONArray products = (JSONArray) JSONPath.eval(joData, "$.data");
        JSONObject pro = (JSONObject) products.get(0);
        BigDecimal creditAmount = ((BigDecimal) JSONPath.eval(pro, "$.creditAmount"));
        BigDecimal advanceInterest = ((BigDecimal) JSONPath.eval(pro, "$.advanceInterest"));
        advanceInterest = advanceInterest != null ? advanceInterest : BigDecimal.valueOf(0);
        page.putField("principalAmount", creditAmount.subtract(advanceInterest));
        page.putField("productId", (JSONPath.eval(pro, "$.productId")));
    }

    @Override
    public Site getSite() {
        return site;
    }
}
