package com.itheima.leyou.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Bean
    public Queue queueStorage(){
        return new Queue("storage_queue", true);
    }

}
