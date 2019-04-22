package com.hawcore.learning.spiderapp.dazhong;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DzdpCommonProcessor implements PageProcessor {


    private Site site = Site.me()
            .setDomain("dianping.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Mobile Safari/53");

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable selectable = html.xpath("//*[@id=\"poi-list\"]/div[3]/div/div[1]/div[1]/ul/li");
        List<Selectable> selectables = selectable.nodes();
        List<DzHotelListInfo> dzHotelListInfos = new ArrayList<>(selectables.size());
        DzHotelListInfo info = null;
        for (Selectable s : selectables) {
            String id = s.regex("<li data-poi=\"(.*?)\" data-index=\".*?\" class=\"hotel-block\">").toString();
            String name = s.regex("<a rel=\"nofollow\" class=\"hotel-name-link\">(.*?)</a>").toString();
            String region = s.regex("<i class=\"icon-position\"></i>.*?<a>(.*?)</a>").toString();
            String position = s.regex("<span class=\"walk-dist\">，(.*?)</span>").toString();
            String price = s.regex("<span class=\"symbol\">￥</span>.*?<strong>(.*?)</strong>").toString();
            String rank = s.regex("<span class=\"sml-rank-stars sml-str(.*?)\"></span>").toString();
            String comments = s.regex("<a data-midas-extends=\"module=5_hotellist_other\" module=\"list-readreview\" rel=\"nofollow\" target=\"_blank\" class=\"comments\">\\((.*?)\\)</a>").toString();
            info = new DzHotelListInfo(Integer.valueOf(id), name, region, position, new BigDecimal(price), Integer.valueOf(rank), Integer.valueOf(comments));
            dzHotelListInfos.add(info);
            System.out.println(info);
        }
        page.putField("dataList", dzHotelListInfos);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public String getUrl(int pageNum) {
        return "http://www.dianping.com/shenzhen/hotel/p" + pageNum;
    }
}
