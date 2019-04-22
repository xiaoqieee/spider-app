package com.hawcore.learning.spiderapp;

import com.hawcore.learning.spiderapp.elm.pipeline.ElemeShopPipeline;
import com.hawcore.learning.spiderapp.elm.processor.ElemeShopProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpiderAppApplication.class, SpiderApplicationTests.class})
public class SpiderApplicationTests<SpringApplicationConfiguration> {

    @Qualifier("ElemeShopPipeline")
    @Autowired
    ElemeShopPipeline elemeShopPipeline;

    @Test
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

}

