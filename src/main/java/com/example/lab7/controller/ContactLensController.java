package com.example.lab7.controller;
import com.example.lab7.dto.ContactLensDto;
import com.example.lab7.serviceInterface.ContactLensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lenses")
public class ContactLensController {

    private final ContactLensService service;

    @GetMapping
    public ResponseEntity<List<ContactLensDto>> getAll() {
        List<ContactLensDto> list = service.getLenses();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactLensDto> getById(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(service.getLens(id)));
    }

    @PostMapping
    public ResponseEntity<ContactLensDto> create(@RequestBody ContactLensDto dto) {
        ContactLensDto created = service.addLens(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactLensDto> update(@PathVariable Long id, @RequestBody ContactLensDto dto) {
        return ResponseEntity.of(Optional.ofNullable(service.updateLens(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteLens(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
