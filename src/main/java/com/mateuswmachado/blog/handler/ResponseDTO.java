package com.mateuswmachado.blog.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private String campo;
    private String erro;

}
