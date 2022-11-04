package com.example.todolist.service;

import com.example.todolist.dto.tag.TagCreateDTO;
import com.example.todolist.dto.tag.TagDTO;
import com.example.todolist.exception.NotFoundException;
import com.example.todolist.mapper.TagMapper;
import com.example.todolist.model.Tag;
import com.example.todolist.repository.TagRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepo tagRepo, TagMapper tagMapper) {
        this.tagRepo = tagRepo;
        this.tagMapper = tagMapper;
    }


    @Override
    public void save(TagCreateDTO tagDTO) {
        Tag tag = tagMapper.toEntity(tagDTO);
        tagRepo.save(tag);
    }

    @Override
    public List<TagDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tag> tags = tagRepo.findAll(pageable);

        return tags.stream()
                .map(tagMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TagDTO tagDTO) {
        Tag tag = tagRepo.findById(tagDTO.getId())
                .orElseThrow(() -> new NotFoundException("error.tag.not.found"));
        tag.setCode(tagDTO.getCode());
        tag.setName(tagDTO.getName());

        tagRepo.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepo.deleteById(id);
    }

}
