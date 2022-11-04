package com.example.todolist.service;

import com.example.todolist.dto.task.TaskCreateDTO;
import com.example.todolist.dto.task.TaskDTO;

import java.util.List;

public interface TaskService {

    void save(TaskCreateDTO taskDTO);

    List<TaskDTO> getAll(int page, int size);

    void update(TaskDTO taskDTO);

    List<TaskDTO> getByStatusId(Long statusId);

    void delete(Long id);

    List<TaskDTO> getByTagId(Long id);

    Boolean isDuplicate(TaskCreateDTO taskCreateDTO);

}
