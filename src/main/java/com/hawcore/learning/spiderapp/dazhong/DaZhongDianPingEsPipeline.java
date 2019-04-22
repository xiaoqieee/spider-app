package com.hawcore.learning.spiderapp.dazhong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class DaZhongDianPingEsPipeline implements Pipeline {

    @Autowired
    private DzEsInfoRepository dzEsInfoRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<DzEsInfo> dzEsInfos = resultItems.get("dataList");
        for (DzEsInfo dzEsInfo : dzEsInfos) {
            dzEsInfoRepository.save(dzEsInfo);
        }

    }
}
