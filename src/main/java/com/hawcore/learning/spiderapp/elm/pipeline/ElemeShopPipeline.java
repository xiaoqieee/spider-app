package com.hawcore.learning.spiderapp.elm.pipeline;

import com.hawcore.learning.spiderapp.elm.mapper.DcShopInfoMapper;
import com.hawcore.learning.spiderapp.elm.model.DcShopInfo;
import com.hawcore.learning.spiderapp.elm.model.ElemeFood;
import com.hawcore.learning.spiderapp.elm.model.ElemeShop;
import com.hawcore.learning.spiderapp.elm.processor.ElemeShopProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

@Component("ElemeShopPipeline")
public class ElemeShopPipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DcShopInfoMapper dcShopInfoMapper;


    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems rs, Task task) {
        if (rs.get("type") != null) {

            ElemeShopProcessor.ProcessType type = rs.get("type");

            switch (type) {
                case SHOP:
                    // 店铺信息处理
                    List<ElemeShop> listShops = rs.get("listShops");
                    for (ElemeShop elemeShop : listShops) {
                        logger.info("save!" + elemeShop);
                        saveShopInfo(elemeShop);
                    }
                    break;
                case FOOD:
                    // 菜品信息处理
                    List<ElemeFood> listFoods = rs.get("listFoods");
                    for (ElemeFood elemeFood : listFoods) {
                        logger.info("save!" + elemeFood);
                    }
                    break;

                default:
                    logger.error("未知处理类型！");
                    break;
            }
        } else {
            // 未抓取到信息
            logger.error("未抓取到需要处理的信息！");
        }
    }

    private void saveShopInfo(ElemeShop elemeShop) {

        DcShopInfo objDcShopInfo = new DcShopInfo();
        BeanUtils.copyProperties(elemeShop, objDcShopInfo);
        objDcShopInfo.setChannelShopId(elemeShop.getId());
        objDcShopInfo.setCreateTime(new Date());

        dcShopInfoMapper.insert(objDcShopInfo);
    }


}

