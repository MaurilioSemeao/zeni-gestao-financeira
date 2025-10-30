package com.msdev.backend.utils;

import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "sprinng")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    UsuarioEntity toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(UsuarioEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaUsuario(UsuarioRequest request, @MappingTarget UsuarioEntity entity);
}
