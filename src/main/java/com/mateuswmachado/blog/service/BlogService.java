package com.mateuswmachado.blog.service;

import com.mateuswmachado.blog.dto.TopicDTO;
import com.mateuswmachado.blog.exception.PersonNotFoundException;
import com.mateuswmachado.blog.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface BlogService {

    Page<TopicDTO> listAllTopics(Pageable pageable);

    ResponseEntity<TopicDTO> getTopicByID(Long id) throws PersonNotFoundException;

    ResponseEntity<String> saveTopic(Topic topic, UriComponentsBuilder uriBuilder);

    ResponseEntity<?> deleteTopicById(Long id) throws PersonNotFoundException;

    ResponseEntity<TopicDTO> updateTopic( Long id, Topic topic);
}
