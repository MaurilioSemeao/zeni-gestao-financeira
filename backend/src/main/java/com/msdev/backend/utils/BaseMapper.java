package com.msdev.backend.utils;

import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;


public interface BaseMapper<T, Req, Res> {


    T toEntity(Req request);

    Res toResponse(T entity);

    void updateEntity(Req request, T entity);

    default List<Res> toResponseList(List<T> entities){
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


}
