package com.hawcore.learning.spiderapp.dazhong;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class DaZhongDianPingPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<DzHotelListInfo> dzHotelListInfos = resultItems.get("dataList");
        System.out.println(dzHotelListInfos);
    }
}
