package com.hawcore.learning.spiderapp.dazhong;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "dzdp_hotel",type = "hotel_origin_data")
public class DzEsInfo implements Serializable {
    @Id
    private Integer id;

    private String text;

    public DzEsInfo(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public DzEsInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
