package com.hawcore.learning.spiderapp.xnol.processor;

import com.hawcore.learning.spiderapp.dazhong.DzHotelListInfo;
import com.hawcore.learning.spiderapp.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XnolProductProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    private Site site = Site.me()
            .setDomain("xiaoniu88.com")
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
        Map<String, String> cookieMap = getCookie();
        for (Map.Entry<String, String> e : cookieMap.entrySet()) {
            site.addCookie(e.getKey(), e.getValue());
        }
        return site;
    }

    public String getUrl() {
        return "https://www.xiaoniu88.com/product/list";
    }

    private Map<String, String> getCookie() {
        String cookie = "_ga=GA1.2.1522612021.1507704380; Hm_lvt_bfadffd6cb8f795e488eccaeb28cab61=1552283587; Hm_lvt_7226b8c48cd07619c7a9ebd471d9d589=1554713563,1555055209,1555663895,1556100929; JSESSIONID=A0C31F2E3EB9EE0AA1C90960D74B5BC9.t-9003; sr=331271.43.11.3.116.7.225.146.0.33.20.15.07; _gid=GA1.2.2022119033.1556245926; referer=\"https://www.xiaoniu88.com/user/_2019-04-26\"; _gat=1; lcksid=5cc2caaca997e7007cbd8746; SESSIONID=cb82c2ce-d593-4ef9-b3d0-60c8cb7a4be2; Hm_lpvt_7226b8c48cd07619c7a9ebd471d9d589=1556269738";
        return CookieUtils.getCookieMap(cookie);
    }
}
