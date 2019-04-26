package com.hawcore.learning.spiderapp.util;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xn025665
 * @date Create on 2019/4/26 19:05
 */
public class RequestHelper {

    public static Request getListRequest() {
        Request request = new Request("https://www.xiaoniu88.com/product/list");
        Map<String, String> cookieMap = getCookie();
        for (Map.Entry<String, String> e : cookieMap.entrySet()) {
            request.addCookie(e.getKey(), e.getValue());
        }
        setHeader(request);
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.form(getParam(), "utf-8"));

        return request;
    }

    public static Request getDetailRequest(Long productId) {
        Request request = new Request("https://www.xiaoniu88.com/product/detail/5/" + productId);
        Map<String, String> cookieMap = getCookie();
        for (Map.Entry<String, String> e : cookieMap.entrySet()) {
            request.addCookie(e.getKey(), e.getValue());
        }
        setHeader(request);
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.form(getParam(), "utf-8"));

        return request;
    }

    private static Map<String, String> getCookie() {
        String cookie = "_ga=GA1.2.1522612021.1507704380; Hm_lvt_bfadffd6cb8f795e488eccaeb28cab61=1552283587; Hm_lvt_7226b8c48cd07619c7a9ebd471d9d589=1554713563,1555055209,1555663895,1556100929; JSESSIONID=A0C31F2E3EB9EE0AA1C90960D74B5BC9.t-9003; _gid=GA1.2.2022119033.1556245926; referer=\"https://www.xiaoniu88.com/user/_2019-04-26\"; sr=331271.43.11.3.116.7.225.146.0.33.20.15.07; _gat=1; lcksid=5cc2e9924bd1f3007c494cc0; SESSIONID=fbe3a103-0065-439f-adab-acae1ecf0c17; Hm_lpvt_7226b8c48cd07619c7a9ebd471d9d589=1556277666";
        return CookieUtils.getCookieMap(cookie);
    }

    private static Map<String, Object> getParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "5");
        map.put("termMode", "2");
        map.put("pageNum", "1");
        map.put("pageSize", "20");
        return map;
    }

    public static void setHeader(Request request) {
        request.addHeader("Accept", " */*");
        request.addHeader("Accept-Encoding", " gzip, deflate, br");
        request.addHeader("Accept-Language", " zh-CN,zh;q=0.9");
        request.addHeader("Connection", " keep-alive");
//        request.addHeader("Content-Length", " 39");
        request.addHeader("Content-Type", " application/x-www-form-urlencoded; charset=UTF-8");
        request.addHeader("Host", " www.xiaoniu88.com");
//        request.addHeader("Cookie", "_ga=GA1.2.1522612021.1507704380; Hm_lvt_bfadffd6cb8f795e488eccaeb28cab61=1552283587; Hm_lvt_7226b8c48cd07619c7a9ebd471d9d589=1554713563,1555055209,1555663895,1556100929; JSESSIONID=A0C31F2E3EB9EE0AA1C90960D74B5BC9.t-9003; _gid=GA1.2.2022119033.1556245926; referer=\"https://www.xiaoniu88.com/user/_2019-04-26\"; sr=331271.43.11.3.116.7.225.146.0.33.20.15.07; lcksid=5cc2de2ba997e7007cbd878f; SESSIONID=cf649bfc-0f52-48a2-b307-fe31b851577a; _gat=1; Hm_lpvt_7226b8c48cd07619c7a9ebd471d9d589=1556274835");
        request.addHeader("Origin", " https://www.xiaoniu88.com");
        request.addHeader("Referer", " https://www.xiaoniu88.com/product/list?type=transfer");
        request.addHeader("User-Agent", " Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 Safari/537.36");
        request.addHeader("X-Requested-With", " XMLHttpRequest");
    }
}
