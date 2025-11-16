package com.example.lab7.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContactLensDto {
    private Long id;
    private String lensType;
    private String lensColor;
    private double lensPower;
    private Long customerId;
    private List<Long> tagIds;
}
