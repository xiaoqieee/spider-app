package com.hawcore.learning.spiderapp.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xn025665
 * @date Create on 2019/4/26 17:12
 */
public class CookieUtils {

    public static Map<String, String> getCookieMap(String cookie) {
        if (StringUtils.isBlank(cookie)) {
            return new HashMap<>(0);
        }
        Map<String, String> cookieMap = new HashMap<>();
        String[] cookies = cookie.split(";");
        for (String c : cookies) {
            String[] cos = c.split("=");
            cookieMap.put(StringUtils.trim(cos[0]), StringUtils.trim(cos[1]));
        }
        return cookieMap;
    }
}
