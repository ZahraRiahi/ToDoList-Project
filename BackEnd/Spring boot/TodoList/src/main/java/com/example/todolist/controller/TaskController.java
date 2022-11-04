package com.example.todolist.controller;

import com.example.todolist.dto.task.TaskCreateDTO;
import com.example.todolist.dto.task.TaskDTO;
import com.example.todolist.dto.validator.TaskCreateDtoValidator;
import com.example.todolist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author zahra tajmir riahi
 */

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@Slf4j
@RequestMapping(value = "/api/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskCreateDtoValidator taskCreateDtoValidator;

    public TaskController(TaskService taskService,
                          TaskCreateDtoValidator taskCreateDtoValidator) {
        this.taskService = taskService;
        this.taskCreateDtoValidator = taskCreateDtoValidator;
    }

    @InitBinder("taskCreateDTO")
    void initTaskCreateDtoValidator(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(taskCreateDtoValidator);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        log.info("REST request to create task: {}", taskCreateDTO);
        taskService.save(taskCreateDTO);
        return ResponseEntity.created(URI.create("/api/task")).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("REST request to get all Status, page: {}, size: {}", page, size);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAll(page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TaskDTO taskDTO) {
        taskService.update(taskDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    void deleteStatus(@PathVariable Long id) {
        taskService.delete(id);
    }

    @GetMapping("/byStatus/{statusId}")
    public ResponseEntity<List<TaskDTO>> getByStatus(@PathVariable Long statusId) {
        List<TaskDTO> tasks = taskService.getByStatusId(statusId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/byTag/{tagId}")
    public ResponseEntity<List<TaskDTO>> getByTagId(@PathVariable Long tagId) {
        List<TaskDTO> tasks = taskService.getByTagId(tagId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}


