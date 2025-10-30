package com.msdev.backend.utils;

import com.msdev.backend.dto.request.ContaRequest;
import com.msdev.backend.dto.response.ContaResponse;
import com.msdev.backend.entity.ContaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContaMapper {


    @Mapping(target = "id", ignore = true)
    ContaEntity toEntity(ContaRequest request);

    ContaResponse toResponse(ContaEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaConta(ContaRequest request, @MappingTarget ContaEntity entity);
}
