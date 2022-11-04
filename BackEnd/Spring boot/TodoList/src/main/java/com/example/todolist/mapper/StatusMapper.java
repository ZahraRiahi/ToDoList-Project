package com.example.todolist.mapper;

import com.example.todolist.dto.status.StatusCreateDTO;
import com.example.todolist.dto.status.StatusDTO;
import com.example.todolist.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public Status toEntity(StatusCreateDTO dto) {
        Status entity = new Status();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());

        return entity;
    }

    public StatusDTO toDTO(Status entity) {
        StatusDTO dto = new StatusDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());

        return dto;
    }

}
