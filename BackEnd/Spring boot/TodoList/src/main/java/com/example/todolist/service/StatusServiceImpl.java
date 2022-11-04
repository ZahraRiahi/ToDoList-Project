package com.example.todolist.service;

import com.example.todolist.dto.status.StatusCreateDTO;
import com.example.todolist.dto.status.StatusDTO;
import com.example.todolist.exception.NotFoundException;
import com.example.todolist.mapper.StatusMapper;
import com.example.todolist.model.Status;
import com.example.todolist.repository.StatusRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class StatusServiceImpl implements StatusService {
    private final StatusRepo statusRepo;
    private final StatusMapper statusMapper;

    public StatusServiceImpl(StatusRepo statusRepo, StatusMapper statusMapper) {
        this.statusRepo = statusRepo;
        this.statusMapper = statusMapper;
    }


    @Override
    public void save(StatusCreateDTO statusCreateDTO) {
        Status status = statusMapper.toEntity(statusCreateDTO);
        statusRepo.save(status);
    }

    @Override
    public List<StatusDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Status> statuses = statusRepo.findAll(pageable);

        return statuses.stream()
                .map(statusMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(StatusDTO statusDTO) {
        Status status = statusRepo.findById(statusDTO.getId())
                .orElseThrow(() -> new NotFoundException("error.status.not.found"));
        status.setCode(statusDTO.getCode());
        status.setName(statusDTO.getName());

        statusRepo.save(status);
    }

    @Override
    public void delete(Long id) {
        statusRepo.deleteById(id);
    }
}
