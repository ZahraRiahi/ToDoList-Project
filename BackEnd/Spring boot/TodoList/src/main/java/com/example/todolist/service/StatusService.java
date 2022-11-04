package com.example.todolist.service;

import com.example.todolist.dto.status.StatusCreateDTO;
import com.example.todolist.dto.status.StatusDTO;

import java.util.List;

public interface StatusService {
    void save(StatusCreateDTO statusCreateDTO);

    List<StatusDTO> getAll(int page, int size);

    void update(StatusDTO statusDTO);

    void delete(Long id);
}
