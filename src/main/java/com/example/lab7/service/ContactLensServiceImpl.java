package com.example.lab7.service;
import com.example.lab7.model.ContactLens;
import com.example.lab7.dto.ContactLensDto;
import com.example.lab7.mapper.ContactLensMapper;
import com.example.lab7.repository.ContactLensRepository;
import com.example.lab7.mapper.TagMapper;
import com.example.lab7.model.Tag;
import com.example.lab7.repository.TagRepository;
import com.example.lab7.repository.CustomerRepository;
import com.example.lab7.serviceInterface.ContactLensService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactLensServiceImpl implements ContactLensService {

    private final ContactLensRepository repository;
    private final CustomerRepository customerRepository;
    private final TagRepository tagRepository;
    private final ContactLensMapper mapper;
    private final TagMapper tagMapper;

    @Override
    public List<ContactLensDto> getLenses() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ContactLensDto addLens(ContactLensDto dto) {
        ContactLens entity = mapper.toEntity(dto);

        if (dto.getCustomerId() != null) {
            customerRepository.findById(dto.getCustomerId()).ifPresent(entity::setCustomer);
        }

        if (dto.getTagIds() != null && !dto.getTagIds().isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(dto.getTagIds());
            entity.setTags(tags);
        }

        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ContactLensDto getLens(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public ContactLensDto updateLens(Long id, ContactLensDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setType(dto.getLensType());
            existing.setColor(dto.getLensColor());
            existing.setPower(dto.getLensPower());

            if (dto.getCustomerId() != null) {
                customerRepository.findById(dto.getCustomerId()).ifPresent(existing::setCustomer);
            } else {
                existing.setCustomer(null);
            }

            if (dto.getTagIds() != null) {
                List<Tag> tags = tagRepository.findAllById(dto.getTagIds());
                existing.setTags(tags);
            }

            return mapper.toDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public boolean deleteLens(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }




}
