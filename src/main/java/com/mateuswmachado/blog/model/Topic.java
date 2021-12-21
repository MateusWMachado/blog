package com.mateuswmachado.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateuswmachado.blog.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity(name = "Topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Campo não pode estar em branco")
    private String title;

    @Column(name = "text")
    @Lob
    @NotBlank(message = "Campo não pode estar em branco")
    private String text;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate timeStamp = LocalDate.now();

}
