package com.example.todolist.dto.task;

public class TaskDTO extends TaskBaseDTO {
    private Long TaskId;

    public Long getTaskId() {
        return TaskId;
    }

    public void setTaskId(Long taskId) {
        TaskId = taskId;
    }
}
