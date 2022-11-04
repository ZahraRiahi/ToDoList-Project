package com.example.todolist.dto.validator;

import com.example.todolist.dto.task.TaskCreateDTO;
import com.example.todolist.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskCreateDtoValidator implements Validator {
    private final TaskService taskService;

    public TaskCreateDtoValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(TaskCreateDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaskCreateDTO request = (TaskCreateDTO) target;

        if (taskService.isDuplicate(request)){
            errors.reject("error.task.is.duplicate");
        }
    }
}
