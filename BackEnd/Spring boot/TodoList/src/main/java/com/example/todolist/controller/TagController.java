package com.example.todolist.controller;

import com.example.todolist.dto.tag.TagCreateDTO;
import com.example.todolist.dto.tag.TagDTO;
import com.example.todolist.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@Slf4j
@RequestMapping(value = "/api/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody TagCreateDTO tagCreateDTO) {
        log.info("REST request to create tag: {}", tagCreateDTO);
        tagService.save(tagCreateDTO);
        return ResponseEntity.created(URI.create("/api/tag")).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TagDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("REST request to get all tags, page: {}, size: {}", page, size);
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getAll(page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TagDTO tagDTO) {
        tagService.update(tagDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    void deleteStatus(@PathVariable Long id) {
        tagService.delete(id);
    }
}
