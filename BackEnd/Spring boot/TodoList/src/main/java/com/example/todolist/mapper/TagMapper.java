package com.example.todolist.mapper;

import com.example.todolist.dto.tag.TagCreateDTO;
import com.example.todolist.dto.tag.TagDTO;
import com.example.todolist.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {


    public Tag toEntity(TagCreateDTO dto) {
        Tag entity = new Tag();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());

        return entity;
    }

    public TagDTO toDTO(Tag entity) {
        TagDTO dto = new TagDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());

        return dto;
    }
}
