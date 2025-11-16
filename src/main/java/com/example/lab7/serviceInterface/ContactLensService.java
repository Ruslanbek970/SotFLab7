package com.example.lab7.serviceInterface;

import com.example.lab7.dto.ContactLensDto;


import java.util.List;

public interface ContactLensService {
    List<ContactLensDto> getLenses();
    ContactLensDto addLens(ContactLensDto dto);
    ContactLensDto getLens(Long id);
    ContactLensDto updateLens(Long id, ContactLensDto dto);
    boolean deleteLens(Long id);



}
