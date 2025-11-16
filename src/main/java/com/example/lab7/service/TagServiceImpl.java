package com.example.lab7.service;

import com.example.lab7.dto.TagDto;
import com.example.lab7.mapper.TagMapper;
import com.example.lab7.model.ContactLens;
import com.example.lab7.model.Tag;
import com.example.lab7.repository.ContactLensRepository;
import com.example.lab7.repository.TagRepository;
import com.example.lab7.serviceInterface.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ContactLensRepository lensRepository;
    private final TagMapper mapper;

    @Override
    public List<TagDto> getAll() {
        return tagRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TagDto create(TagDto dto) {
        return mapper.toDto(tagRepository.save(mapper.toEntity(dto)));
    }

    @Override
    public TagDto getById(Long id) {
        return tagRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public TagDto update(Long id, TagDto dto) {
        return tagRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            return mapper.toDto(tagRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        if (!tagRepository.existsById(id)) return false;
        tagRepository.deleteById(id);
        return true;
    }

    @Override
    public TagDto assignLens(Long tagId, Long lensId) {
        Optional<Tag> tagOpt = tagRepository.findById(tagId);
        Optional<ContactLens> lensOpt = lensRepository.findById(lensId);
        if (tagOpt.isEmpty() || lensOpt.isEmpty()) return null;

        Tag tag = tagOpt.get();
        ContactLens lens = lensOpt.get();

        if (!tag.getLenses().contains(lens)) {
            tag.getLenses().add(lens);
        }
        if (!lens.getTags().contains(tag)) {
            lens.getTags().add(tag);
        }

        tagRepository.save(tag);
        return mapper.toDto(tag);
    }

    @Override
    public TagDto unassignLens(Long tagId, Long lensId) {
        Optional<Tag> tagOpt = tagRepository.findById(tagId);
        Optional<ContactLens> lensOpt = lensRepository.findById(lensId);
        if (tagOpt.isEmpty() || lensOpt.isEmpty()) return null;

        Tag tag = tagOpt.get();
        ContactLens lens = lensOpt.get();

        tag.getLenses().remove(lens);
        lens.getTags().remove(tag);

        tagRepository.save(tag);
        return mapper.toDto(tag);
    }
}
