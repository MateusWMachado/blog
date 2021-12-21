package com.mateuswmachado.blog.exception;

import com.mateuswmachado.blog.service.BlogService;
import com.mateuswmachado.blog.service.BlogServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;




class PersonNotFoundExceptionTest {

    @InjectMocks
    BlogService blogService = new BlogServiceImpl();


    @Test
    public void testExceptionMessage() {
        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () ->
            blogService.getTopicByID(4L), "Teste");



        assertTrue(exception.getMessage().contains("Person not found with id"));
    }


}