package com.example.todolist.service;

import com.example.todolist.dto.task.TaskCreateDTO;
import com.example.todolist.dto.task.TaskDTO;
import com.example.todolist.exception.NotFoundException;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.model.Task;
import com.example.todolist.repository.StatusRepo;
import com.example.todolist.repository.TagRepo;
import com.example.todolist.repository.TaskRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final StatusRepo statusRepo;
    private final TagRepo tagService;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepo taskRepo,
                           StatusRepo statusService,
                           TagRepo tagService,
                           TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.statusRepo = statusService;
        this.tagService = tagService;
        this.taskMapper = taskMapper;
    }

    @Override
    public void save(TaskCreateDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        taskRepo.save(task);
    }

    @Override
    public List<TaskDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepo.findAll(pageable);

        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TaskDTO taskDTO) {
        Task task = taskRepo.findById(taskDTO.getTaskId())
                        .orElseThrow(() -> new NotFoundException("error.task.not.found"));
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setStatus(statusRepo.getOne(taskDTO.getStatusId()));
        task.setTag(tagService.getOne(taskDTO.getTagId()));

        taskRepo.save(task);
    }

    @Override
    public List<TaskDTO> getByStatusId(Long statusId) {
        List<Task> taskList = taskRepo.findByStatusId(statusId);
        return taskList.stream().map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public List<TaskDTO> getByTagId(Long id) {
        List<Task> taskList = taskRepo.findByTagId(id);

        return taskList.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isDuplicate(TaskCreateDTO taskCreateDTO) {
        Optional<Task> duplicate = taskRepo.findDuplicateTask(taskCreateDTO.getTaskDescription(),
                taskCreateDTO.getStatusId(),
                taskCreateDTO.getTagId());

        return duplicate.isPresent();
    }
}
