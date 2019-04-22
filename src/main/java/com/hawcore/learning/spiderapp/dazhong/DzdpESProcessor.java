package com.hawcore.learning.spiderapp.dazhong;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DzdpESProcessor implements PageProcessor {


    private Site site = Site.me()
            .setDomain("dianping.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Mobile Safari/53");

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable selectable = html.xpath("//*[@id=\"poi-list\"]/div[3]/div/div[1]/div[1]/ul/li");
        List<Selectable> selectables = selectable.nodes();
        List<DzEsInfo> dzEsInfos = new ArrayList<>(selectables.size());
        for (Selectable s : selectables) {
            String id = s.regex("<li data-poi=\"(.*?)\" data-index=\".*?\" class=\"hotel-block\">").toString();
            dzEsInfos.add(new DzEsInfo(Integer.valueOf(id), s.toString()));
            System.out.println(s.toString());
        }
        page.putField("dataList", dzEsInfos);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public String getUrl(int pageNum) {
        return "http://www.dianping.com/shenzhen/hotel/p" + pageNum;
    }
}
