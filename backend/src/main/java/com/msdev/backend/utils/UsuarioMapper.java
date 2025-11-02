package com.msdev.backend.utils;

import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper  extends BaseMapper<UsuarioEntity, UsuarioRequest, UsuarioResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    UsuarioEntity toEntity(UsuarioRequest request);

    @Override
    UsuarioResponse toResponse(UsuarioEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UsuarioRequest request, @MappingTarget UsuarioEntity entity);
}
