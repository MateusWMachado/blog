package com.mateuswmachado.blog.service;

import com.mateuswmachado.blog.dto.TopicDTO;
import com.mateuswmachado.blog.exception.PersonNotFoundException;
import com.mateuswmachado.blog.kafka.producer.TopicProducer;
import com.mateuswmachado.blog.model.Topic;
import com.mateuswmachado.blog.repository.BlogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class BlogServiceImpl implements BlogService{

    private BlogRepository repository;
    private ModelMapper mapper;
    private TopicProducer topicProducer;

    @Override
    public Page<TopicDTO> listAllTopics(Pageable pageable) {
        Page<Topic> all = repository.findAll(pageable);
        return new PageImpl(all.stream().map(topic -> mapper.map(topic, TopicDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TopicDTO> getTopicByID(Long id) throws PersonNotFoundException {
        Topic topic = verifyIfTopicExists(id);
        return ResponseEntity.ok().body(mapper.map(topic, TopicDTO.class));
    }

    @Override
    public ResponseEntity<String> saveTopic(Topic topic, UriComponentsBuilder uriBuilder) {
        Topic savedTopic = repository.save(topic);
        topicProducer.send(mapper.map(topic, TopicDTO.class));
        URI uri = uriBuilder.path("/v1/{id}").buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(uri).body("Topic with id "+ savedTopic.getId()+ " saved");
    }

    @Override
    public ResponseEntity<?> deleteTopicById(Long id) throws PersonNotFoundException {
        repository.delete(verifyIfTopicExists(id));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TopicDTO> updateTopic(Long id, Topic topic) {
        return repository.findById(id).map(newTopic -> {
            newTopic.setTitle(topic.getTitle());
            newTopic.setText(topic.getText());
            Topic save = repository.save(newTopic);
            return ResponseEntity.ok(mapper.map(save, TopicDTO.class));
        }).orElse(ResponseEntity.notFound().build());
    }

    public Topic verifyIfTopicExists(Long id) throws PersonNotFoundException {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
