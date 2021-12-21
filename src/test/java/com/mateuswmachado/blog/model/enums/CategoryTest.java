package com.mateuswmachado.blog.model.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void textEnumText() {
        Assertions.assertEquals("HELP", String.valueOf(Category.HELP));
        Assertions.assertEquals("JAVA", String.valueOf(Category.JAVA));
        Assertions.assertEquals("PYTHON", String.valueOf(Category.PYTHON));
        Assertions.assertEquals("DAY", String.valueOf(Category.DAY));
    }

}