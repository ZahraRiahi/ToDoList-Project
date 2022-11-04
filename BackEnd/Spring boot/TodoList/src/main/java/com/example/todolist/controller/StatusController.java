package com.example.todolist.controller;

import com.example.todolist.dto.status.StatusCreateDTO;
import com.example.todolist.dto.status.StatusDTO;
import com.example.todolist.service.StatusService;
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
@RequestMapping(value = "/api/status")

public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody StatusCreateDTO statusCreateDTO) {
        log.info("REST request to create status: {}", statusCreateDTO);
        statusService.save(statusCreateDTO);
        return ResponseEntity.created(URI.create("/api/status")).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StatusDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("REST request to get all statuses, page: {}, size: {}", page, size);
        return ResponseEntity.status(HttpStatus.OK).body(statusService.getAll(page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody StatusDTO statusDTO) {
        statusService.update(statusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    void deleteStatus(@PathVariable Long id) {
        statusService.delete(id);
    }
}
