package com.example.lab7.mapper;

import com.example.lab7.dto.ContactLensDto;
import com.example.lab7.model.ContactLens;
import com.example.lab7.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContactLensMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "tagIds", source = "tags")
    @Mapping(source = "type", target = "lensType")
    @Mapping(source = "color", target = "lensColor")
    @Mapping(source = "power", target = "lensPower")
    ContactLensDto toDto(ContactLens entity);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(source = "lensType", target = "type")
    @Mapping(source = "lensColor", target = "color")
    @Mapping(source = "lensPower", target = "power")
    ContactLens toEntity(ContactLensDto dto);

    default List<Long> mapTagsToIds(List<Tag> tags) {
        if (tags == null) return null;
        return tags.stream().map(Tag::getId).collect(Collectors.toList());
    }
}
