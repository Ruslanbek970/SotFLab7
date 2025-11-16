package com.example.lab7.controller;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.dto.ContactLensDto;
import com.example.lab7.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> list = service.getAll();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        CustomerDto created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody CustomerDto dto) {
        return ResponseEntity.of(Optional.ofNullable(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/lenses")
    public ResponseEntity<List<ContactLensDto>> getLenses(@PathVariable Long id) {
        List<ContactLensDto> lenses = service.getLensesForCustomer(id);
        return lenses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lenses);
    }
}
