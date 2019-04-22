package com.hawcore.learning.spiderapp.dazhong;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface DzEsInfoRepository extends ElasticsearchRepository<DzEsInfo, Integer> {
}
