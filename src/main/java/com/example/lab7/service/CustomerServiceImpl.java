package com.example.lab7.service;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.dto.ContactLensDto;
import com.example.lab7.mapper.CustomerMapper;
import com.example.lab7.mapper.ContactLensMapper;
import com.example.lab7.model.Customer;
import com.example.lab7.model.ContactLens;
import com.example.lab7.repository.CustomerRepository;
import com.example.lab7.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final ContactLensMapper lensMapper;

    @Override
    public List<CustomerDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public CustomerDto create(CustomerDto dto) {
        Customer saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public CustomerDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            return mapper.toDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<ContactLensDto> getLensesForCustomer(Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id = " + customerId));

        List<ContactLens> lenses = customer.getLenses();
        return lenses.stream().map(lensMapper::toDto).collect(Collectors.toList());
    }
}
