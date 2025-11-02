package com.msdev.backend.utils;

import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BaseMapper<T, Req, Res> {

    @Mapping(target = "id", ignore = true)
    T toEntity(Req request);

    Res toResponse(T entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(Req request, @MappingTarget T entity);

    default List<Res> toResponseList(List<T> entities){
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


}
