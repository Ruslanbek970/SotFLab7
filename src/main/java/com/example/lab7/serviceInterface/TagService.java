package com.example.lab7.serviceInterface;

import com.example.lab7.dto.TagDto;
import java.util.List;

public interface TagService {
    List<TagDto> getAll();
    TagDto create(TagDto dto);
    TagDto getById(Long id);
    TagDto update(Long id, TagDto dto);
    boolean delete(Long id);

    TagDto assignLens(Long tagId, Long lensId);
    TagDto unassignLens(Long tagId, Long lensId);
}
