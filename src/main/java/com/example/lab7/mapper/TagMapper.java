package com.example.lab7.mapper;

import com.example.lab7.dto.TagDto;
import com.example.lab7.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag entity);
    Tag toEntity(TagDto dto);
}
