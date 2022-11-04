package com.example.todolist.dto.task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaskBaseDTO {

    private String taskDescription;
    private Long statusId;
    private Long tagId;

    @NotNull
    @NotBlank
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @NotNull
    @NotBlank
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @NotNull
    @NotBlank
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
