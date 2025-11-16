package com.example.lab7.serviceInterface;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.dto.ContactLensDto;


import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll();
    CustomerDto create(CustomerDto dto);
    CustomerDto getById(Long id);
    CustomerDto update(Long id, CustomerDto dto);
    boolean delete(Long id);
    List<ContactLensDto> getLensesForCustomer(Long customerId);
}
