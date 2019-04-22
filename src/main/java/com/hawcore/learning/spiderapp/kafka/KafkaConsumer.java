package com.hawcore.learning.spiderapp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author xn025665
 * @date Create on 2019/4/22 13:12
 */
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"app_log"})
    public void receive(String message) {
        System.out.println("app_log--消费消息:" + message);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
