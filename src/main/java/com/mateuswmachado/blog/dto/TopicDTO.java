package com.mateuswmachado.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TopicDTO {

    private Long id;
    private String title;
    private String text;
    private LocalDate timeStamp;

}
