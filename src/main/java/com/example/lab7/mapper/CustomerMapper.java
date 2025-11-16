package com.example.lab7.mapper;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);
    Customer toEntity(CustomerDto dto);
}
