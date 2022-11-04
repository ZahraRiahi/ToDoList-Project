package com.example.todolist.mapper;

import com.example.todolist.dto.task.TaskCreateDTO;
import com.example.todolist.dto.task.TaskDTO;
import com.example.todolist.dto.task.TaskDetailDTO;
import com.example.todolist.model.Task;
import com.example.todolist.repository.StatusRepo;
import com.example.todolist.repository.TagRepo;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final StatusRepo statusRepo;
    private final TagRepo tagRepo;

    public TaskMapper(StatusRepo statusRepo, TagRepo tagRepo) {
        this.statusRepo = statusRepo;
        this.tagRepo = tagRepo;
    }


    public Task toEntity(TaskCreateDTO dto) {
        Task entity = new Task();
        entity.setTaskDescription(dto.getTaskDescription());
        entity.setStatus(statusRepo.getOne(dto.getStatusId()));
        entity.setTag(tagRepo.getOne(dto.getTagId()));

        return entity;
    }

    public TaskDetailDTO toDTO(Task entity) {
        TaskDetailDTO dto = new TaskDetailDTO();
        dto.setTaskId(entity.getId());
        dto.setTaskDescription(entity.getTaskDescription());
        dto.setTagId(entity.getTag().getId());
        dto.setTagCode(entity.getTag().getCode());
        dto.setTagName(entity.getTag().getName());
        dto.setStatusId(entity.getStatus().getId());
        dto.setStatusCode(entity.getStatus().getCode());
        dto.setStatusName(entity.getStatus().getName());

        return dto;
    }
}
