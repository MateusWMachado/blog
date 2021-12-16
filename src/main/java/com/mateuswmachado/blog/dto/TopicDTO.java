package com.mateuswmachado.blog.dto;

import com.mateuswmachado.blog.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TopicDTO implements Serializable {

    private Long id;
    private String title;
    private String text;
    private LocalDate timeStamp;
    private Category category;

}
