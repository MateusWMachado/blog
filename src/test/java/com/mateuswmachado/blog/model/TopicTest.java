package com.mateuswmachado.blog.model;

import com.mateuswmachado.blog.model.enums.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TopicTest {

    @Test
    public void testCreateNewTopic() {
        Topic t1 = new Topic(1L, "Spring", "Text", Category.HELP,LocalDate.now());
        Assertions.assertEquals(1L, t1.getId());
        Assertions.assertEquals("Spring", t1.getTitle());
        Assertions.assertEquals("Text", t1.getText());
        Assertions.assertEquals(LocalDate.now(), t1.getTimeStamp());
        Assertions.assertEquals("HELP", String.valueOf(t1.getCategory()));

    }

}