package com.hawcore.learning.spiderapp.xnol.handler;

import com.hawcore.learning.spiderapp.util.RequestHelper;
import com.hawcore.learning.spiderapp.xnol.pipeline.XnolProductPipeline;
import com.hawcore.learning.spiderapp.xnol.processor.XnolProductProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class XnolProductHandler {

    private Logger logger = LoggerFactory.getLogger(XnolProductHandler.class);

    @Autowired
    private XnolProductPipeline xnolProductPipeline;

    @Scheduled(cron = "00/2 * * * * ?")
    public void doScan() {
        try {
            Spider.create(new XnolProductProcessor()).addRequest(RequestHelper.getListRequest()).addPipeline(xnolProductPipeline).thread(1).run();
        } catch (Exception e) {
            logger.error("总异常", e);
        }
    }
}
