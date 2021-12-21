package com.mateuswmachado.blog.service;

import com.mateuswmachado.blog.dto.TopicDTO;
import com.mateuswmachado.blog.exception.PersonNotFoundException;
import com.mateuswmachado.blog.model.Topic;
import com.mateuswmachado.blog.model.enums.Category;
import com.mateuswmachado.blog.repository.BlogRepository;
import org.assertj.core.util.IterableUtil;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    private final BlogService service = new BlogServiceImpl();

    @Mock
    private BlogRepository repository;

    /*@Mock
    private ModelMapper mapper;*/

    ModelMapper mapper = new ModelMapper();

    @Test
    void testListAllTopics() {
        List<Topic> aa = new ArrayList<>();
        aa.add(new Topic());
        aa.add(new Topic());
        Mockito.when(repository.findAll(Pageable.unpaged())).thenReturn(new PageImpl(new ArrayList<>(aa)));

        Page<TopicDTO> all = service.listAllTopics(Pageable.unpaged());
        Assertions.assertEquals(aa.size(), all.getSize());
    }

    @Test
    void testGetTopicByID() throws PersonNotFoundException {
        TopicDTO t1 = new TopicDTO(1L, "Spring Boot BLOG", "Meu primeiro post", LocalDate.of(2021, 12, 15), null);
        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(mapper.map(t1, Topic.class)));
        ResponseEntity<TopicDTO> topicByID = service.getTopicByID(1L);

        assertEquals(t1, topicByID.getBody());
    }

    @Test
    void testSaveTopic() {

        Topic t1 = new Topic(1L, "Spring", "Text", Category.HELP,LocalDate.now());
        ResponseEntity<String> stringResponseEntity = service.saveTopic(t1, UriComponentsBuilder.newInstance());

        //ResponseEntity<String> stringResponseEntity = service.saveTopic(t1, UriComponentsBuilder uriBuilder);
    }

    @Test
    void deleteTopicById() {
    }

    @Test
    void updateTopic() {
    }

    @Test
    void verifyIfTopicExists() {
    }
}