package com.hawcore.learning.spiderapp.dazhong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DzEsInfoService {

    @Autowired
    private DzEsInfoRepository dzEsInfoRepository;

    public Iterable<DzEsInfo> findAll() {
        Iterable<DzEsInfo> dzEsInfos = dzEsInfoRepository.findAll();
        dzEsInfos.forEach((a) -> {
            System.out.println(a.getId() + ":" + a.getText());
        });
        return dzEsInfos;
    }
}
