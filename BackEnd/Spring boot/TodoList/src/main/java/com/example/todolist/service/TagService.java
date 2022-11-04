package com.example.todolist.service;

import com.example.todolist.dto.tag.TagCreateDTO;
import com.example.todolist.dto.tag.TagDTO;

import java.util.List;

public interface TagService {

    void save(TagCreateDTO tagCreateDTO);

    List<TagDTO> getAll(int page, int size);

    void update(TagDTO tagDTO);

    void delete(Long id);

}
