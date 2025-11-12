package com.msdev.backend.utils;

import com.msdev.backend.dto.request.ParcelamentoRequest;
import com.msdev.backend.dto.response.ParcelamentoResponse;
import com.msdev.backend.entity.ParcelamentoEntity;
import com.msdev.backend.repository.ParcelamentoRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParcelamentoMapper extends  BaseMapper<ParcelamentoEntity, ParcelamentoRequest, ParcelamentoResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    ParcelamentoEntity toEntity(ParcelamentoRequest request);

    @Override
    ParcelamentoResponse toResponse(ParcelamentoEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ParcelamentoRequest request, @MappingTarget ParcelamentoEntity entity);

}