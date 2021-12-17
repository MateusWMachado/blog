package com.mateuswmachado.blog.kafka.consumer;

import com.mateuswmachado.blog.dto.TopicDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    private static final Logger log = LoggerFactory.getLogger(TopicConsumer.class);

    @Value(value = "${topic.name}")
    private String topic;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "topicDTOConcurrentKafkaListenerContainerFactory")
    public void listenTopic(ConsumerRecord<String, TopicDTO> record) {
        log.info("Received Message "+record.partition());
        log.info("Received Message "+ record.value());
    }
}
