package com.mateuswmachado.blog.dto;

import com.mateuswmachado.blog.model.enums.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
class TopicDTOTest {

    @Test
    public void testUserDTO() {
        TopicDTO t1 = new TopicDTO(1L, "Spring", "Text", LocalDate.now(), Category.HELP );
        Assertions.assertEquals(1L, t1.getId());
        Assertions.assertEquals("Spring", t1.getTitle());
        Assertions.assertEquals("Text", t1.getText());
        Assertions.assertEquals(LocalDate.now(), t1.getTimeStamp());
        Assertions.assertEquals("HELP", String.valueOf(t1.getCategory()));
    }

}