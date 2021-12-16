package com.mateuswmachado.blog.controller;

import com.mateuswmachado.blog.dto.TopicDTO;
import com.mateuswmachado.blog.exception.PersonNotFoundException;
import com.mateuswmachado.blog.model.Topic;
import com.mateuswmachado.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BlogController {

    private BlogService blogService;

    @GetMapping
    @Cacheable(value = "listTopics")
    public Page<TopicDTO> listAll(Pageable pageable) {
        return blogService.listAllTopics(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getByID(@PathVariable Long id) throws PersonNotFoundException {
        return blogService.getTopicByID(id);
    }

    @PostMapping
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<String> save(@Valid @RequestBody Topic topic, UriComponentsBuilder uriBuilder){
        return blogService.saveTopic(topic, uriBuilder);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) throws PersonNotFoundException {
        return blogService.deleteTopicById(id);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody Topic topic) {
        return blogService.updateTopic(id, topic);
    }


}

