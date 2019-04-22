package com.hawcore.learning.spiderapp.controller;

import com.hawcore.learning.spiderapp.dazhong.*;
import com.hawcore.learning.spiderapp.elm.pipeline.ElemeShopPipeline;
import com.hawcore.learning.spiderapp.elm.processor.ElemeShopProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

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

    @RequestMapping("/test/{name}")
    public String test(@PathVariable("name") String name) {
//        testElemeShopSpider();
        testDzdpSpider();
        return "Hello " + name;
    }

    @RequestMapping("/getall")
    public Iterable<DzEsInfo> getAll(){
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
}
