package com.mateuswmachado.blog.kafka.producer;

import com.mateuswmachado.blog.dto.TopicDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicProducer {

    private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);
    private final String topic;
    private final KafkaTemplate<String, TopicDTO> kafkaTemplate;

    public TopicProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, TopicDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(TopicDTO topicDTO) {
        kafkaTemplate.send(topic, topicDTO).addCallback(
                success -> logger.info("Message send " + success.getProducerRecord().value()),
                failure -> logger.info("Message failure " + failure.getMessage())
        );
    }

}

