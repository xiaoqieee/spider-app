package com.hawcore.learning.spiderapp.elm.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.hawcore.learning.spiderapp.elm.model.ElemeFood;
import com.hawcore.learning.spiderapp.elm.model.ElemeShop;
import org.elasticsearch.common.util.set.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElemeShopProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    private Site site = Site.me()
            .setDomain("ele.me")
            .addHeader("referer", "https://h5.ele.me/")
            .setSleepTime(100)
            .setAcceptStatCode(Sets.newHashSet(200, 401))
            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Mobile Safari/53")
            .addCookie("ubt_ssid", "poqr3yxnqlv098kft9exs0diblbp2cdr_2019-04-20")
            .addCookie("perf_ssid", "yyldqgdx7r5h4ks83sc9gjp4f7x85z8h_2019-04-20")
            .addCookie("cna", "xJtRFMMYUy8CAWVqaWGebyWG")
            .addCookie("_utrace", "30222b57609b15579676668dd2cfbeea_2019-04-20")
            .addCookie("_bl_uid", "ehjvhuaIpOvkn043RvmniCj8Uds1")
            .addCookie("track_id", "1555768100|24e1cdf4bd6a531e83a859a14c2902b5c2a1b2ff61f8a8a939|b110dd16f3cac23af689d6b6691f593d")
            .addCookie("USERID", "59500770")
            .addCookie("UTUSER", "59500770")
            .addCookie("SID", "TVboqBrSXSwaR4VPabeV1tjOp5UyrNYBEkLA")
            .addCookie("isg", "BN_f6uIn_y2KmPsxQ4_MxpRzbjOp7DLg2JFUHnEsew6SAP-CeRTDNl3extbbngte");

    private String longitude;
    private String latitude;
    private Boolean crawlMenu = false; // 是否抓取菜单信息

    /*
     * 数据处理类型
     */
    public static enum ProcessType {
        SHOP, FOOD
    }

    /**
     * 饿了么店铺爬虫实例化方法
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param crawlMenu 是否抓取菜单
     */
    public ElemeShopProcessor(String longitude, String latitude,
                              Boolean crawlMenu) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.crawlMenu = crawlMenu;
    }


    @Override
    public void process(Page page) {
        // H5列表数据
        if (page.getUrl()
                .regex("https://h5\\.ele\\.me/restapi/shopping/v3/restaurants+")
                .match()) {
            logger.info("解析H5商户列表数据");
            logger.debug(page.getRawText());

            JSONObject joData = JSON.parseObject(page.getRawText());
            JSONArray jaRestaurant = (JSONArray) JSONPath.eval(joData, "$.items.restaurant");

            // 解析餐厅数据
            if (jaRestaurant != null) {
                ArrayList<ElemeShop> listShops = new ArrayList<>();
                for (Object oRest : jaRestaurant) {
                    // 遍历餐厅数据，解析为ElemeShop对象
                    JSONObject joRest = (JSONObject) oRest;
                    ElemeShop objShop = JSONObject.toJavaObject(joRest, ElemeShop.class);
                    logger.debug(objShop.toString());
                    listShops.add(objShop);
                    if (crawlMenu) {
                        // 需要抓取菜单信息，加入后续targetRequest
                        String targetUrl = "https://www.ele.me/restapi/shopping/v2/menu?restaurant_id=" + objShop.getId() + "&terminal=web";
                        page.addTargetRequest(targetUrl);
                    }
                }
                page.putField("type", ProcessType.SHOP);
                page.putField("listShops", listShops);
            }
        } else {
            logger.info("解析菜品");
            logger.debug(page.getRawText());
            Set<String> setItemIds = new HashSet<>(); // 保存itemId用于菜品去重

            JSONArray jaMenuGroup = JSON.parseArray(page.getRawText());
            if (jaMenuGroup != null) {
                List<ElemeFood> listFoods = new ArrayList<>();
                for (Object oMenuGroup : jaMenuGroup) {
                    JSONArray jaFoods = (JSONArray) JSONPath.eval(oMenuGroup, "$.foods");
                    for (Object oFood : jaFoods) {
                        JSONObject joFood = (JSONObject) oFood;
                        // 获取每个菜品数据
                        String strItemId = (String) JSONPath.eval(joFood, "$.item_id"); // itemId
                        // 判断是否重复
                        if (setItemIds.contains(strItemId)) {
                            // 重复菜品，跳过
                            continue;
                        } else {
                            setItemIds.add(strItemId);
                        }

                        ElemeFood objFood = JSONObject.toJavaObject(joFood, ElemeFood.class);
                        logger.debug(objFood.toString());
                        listFoods.add(objFood);

                    }
                }
                page.putField("type", ProcessType.FOOD);
                page.putField("listFoods", listFoods);
            }
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 获取起始url
     *
     * @return
     */
    public String getUrl() {
        // 门店查询页面 https://www.ele.me/place/wtw0w37dxs0r?latitude=31.032709&longitude=121.217287
        // 门店里列表json https://h5.ele.me/restapi/shopping/v3/restaurants?latitude=31.107641&longitude=121.252976&offset=0&limit=1&extras[]=activities&extras[]=tags&terminal=h5
        // 店铺json https://www.ele.me/shop/E7326872827353281855
    	/*String url = "https://www.ele.me/place/";
    	String geohash = "wtw0w37dxs0r";*/

        String url = "https://h5.ele.me/restapi/shopping/v3/restaurants?latitude=" + latitude + "&longitude=" + longitude + "&offset=0&limit=3&extras[]=activities&extras[]=tags&terminal=h5";
        return "https://h5.ele.me/restapi/shopping/v3/restaurants?latitude=22.54286&longitude=114.059563&offset=24&limit=8&extras[]=activities&extras[]=tags&extra_filters=home&rank_id=37deca9128684c8d863974874f119242&terminal=h5";
    }
}
