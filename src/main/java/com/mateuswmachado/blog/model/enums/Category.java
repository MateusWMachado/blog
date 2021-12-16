package com.mateuswmachado.blog.model.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Category {

    HELP("Help"),
    JAVA("Java"),
    PYTHON("Python"),
    DAY("Dia a dia ");

    private String descricao;

    Category(String text){
        this.descricao = text;
    }

}
