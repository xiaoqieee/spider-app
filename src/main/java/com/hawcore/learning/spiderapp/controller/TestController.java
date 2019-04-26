package com.hawcore.learning.spiderapp.controller;

import com.hawcore.learning.spiderapp.dazhong.*;
import com.hawcore.learning.spiderapp.elm.pipeline.ElemeShopPipeline;
import com.hawcore.learning.spiderapp.elm.processor.ElemeShopProcessor;
import com.hawcore.learning.spiderapp.kafka.KafkaProducer;
import com.hawcore.learning.spiderapp.util.CookieUtils;
import com.hawcore.learning.spiderapp.xnol.processor.XnolProductProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Qualifier("ElemeShopPipeline")
    @Autowired
    ElemeShopPipeline elemeShopPipeline;

    @Autowired
    private DaZhongDianPingPipeline daZhongDianPingPipeline;
    @Autowired
    private DaZhongDianPingEsPipeline daZhongDianPingEsPipeline;

    @Autowired
    private DzEsInfoService dzEsInfoService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/test/{name}")
    public String test(@PathVariable("name") String name) {
//        testElemeShopSpider();
        testDzdpSpider();
        return "Hello " + name;
    }

    @RequestMapping("/getall")
    public Iterable<DzEsInfo> getAll() {
        return dzEsInfoService.findAll();
    }


    public void testElemeShopSpider() {
        String latitude = "22.54286";
        String longitude = "114.059563";
        ElemeShopProcessor processor = new ElemeShopProcessor(longitude, latitude, false);
        Spider.create(processor).addPipeline(elemeShopPipeline)
                .addUrl(processor.getUrl())
                // 开启1个线程抓取
                .thread(1)
                // 启动爬虫
                .run();
    }

    public void testDzdpSpider() {
        for (int i = 1; i <= 50; i++) {
//            DaZhongDianPingProcessor daZhongDianPingProcessor = new DaZhongDianPingProcessor();
//            Spider.create(daZhongDianPingProcessor).addPipeline(daZhongDianPingPipeline)
//                    .addUrl(daZhongDianPingProcessor.getUrl(i)).thread(1).run();

            DzdpESProcessor daZhongDianPingProcessor = new DzdpESProcessor();
            Spider.create(new DzdpESProcessor()).addPipeline(daZhongDianPingEsPipeline)
                    .addUrl(daZhongDianPingProcessor.getUrl(i)).thread(1).run();
        }

    }

    @RequestMapping("/send")
    public void send() {
        kafkaProducer.send();
    }


    @RequestMapping("/xnol")
    public void xnol() {
        while (true) {
            Request request = new Request("https://www.xiaoniu88.com/product/list");
            Map<String, String> cookieMap = getCookie();
            for (Map.Entry<String, String> e : cookieMap.entrySet()) {
                request.addCookie(e.getKey(), e.getValue());
            }
            request.addHeader("Origin", "https://www.xiaoniu88.com");
            request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 Safari/537.36");
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.json("{'type':5,'termMode':2,'pageNum':1,'pageSize':10}", "utf-8"));
            Spider.create(new XnolProductProcessor()).addRequest(request).thread(1).run();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
            }
        }
    }
    private Map<String, String> getCookie() {
        String cookie = "_ga=GA1.2.1522612021.1507704380; Hm_lvt_bfadffd6cb8f795e488eccaeb28cab61=1552283587; Hm_lvt_7226b8c48cd07619c7a9ebd471d9d589=1554713563,1555055209,1555663895,1556100929; JSESSIONID=A0C31F2E3EB9EE0AA1C90960D74B5BC9.t-9003; sr=331271.43.11.3.116.7.225.146.0.33.20.15.07; _gid=GA1.2.2022119033.1556245926; referer=\"https://www.xiaoniu88.com/user/_2019-04-26\"; _gat=1; Hm_lpvt_7226b8c48cd07619c7a9ebd471d9d589=1556271380; lcksid=5cc2d11ca997e7007cbd8762; SESSIONID=c50056c9-aa9e-4958-ae17-bcb4e46246ca";
        return CookieUtils.getCookieMap(cookie);
    }

}
