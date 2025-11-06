package com.msdev.backend.utils;

import com.msdev.backend.dto.request.ContaRequest;
import com.msdev.backend.dto.response.ContaResponse;
import com.msdev.backend.entity.ContaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContaMapper extends BaseMapper<ContaEntity, ContaRequest, ContaResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    ContaEntity toEntity(ContaRequest request);

    @Override
    ContaResponse toResponse(ContaEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ContaRequest request, @MappingTarget ContaEntity entity);
}
