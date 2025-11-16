package com.example.lab7.controller;

import com.example.lab7.dto.TagDto;
import com.example.lab7.serviceInterface.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAll() {
        List<TagDto> list = service.getAll();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<TagDto> create(@RequestBody TagDto dto) {
        TagDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(@PathVariable Long id, @RequestBody TagDto dto) {
        return ResponseEntity.of(Optional.ofNullable(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{tagId}/as-l/{lensId}")
    public ResponseEntity<TagDto> assignLens(@PathVariable Long tagId, @PathVariable Long lensId) {
        TagDto dto = service.assignLens(tagId, lensId);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{tagId}/unas-l/{lensId}")
    public ResponseEntity<TagDto> unassignLens(@PathVariable Long tagId, @PathVariable Long lensId) {
        TagDto dto = service.unassignLens(tagId, lensId);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
