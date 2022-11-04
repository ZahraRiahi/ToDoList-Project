package com.example.todolist.dto.tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TagBase {
    private String code;
    private String name;

    @NotNull
    @NotBlank
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
