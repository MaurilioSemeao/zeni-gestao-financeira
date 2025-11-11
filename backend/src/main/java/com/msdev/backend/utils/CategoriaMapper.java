package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.request.CategoriaRequest;
import com.msdev.backend.dto.response.CategoriaResponse;
import com.msdev.backend.entity.CarteiraEntity;
import com.msdev.backend.entity.CategoriaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<CategoriaEntity, CategoriaRequest, CategoriaResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    CategoriaEntity toEntity(CategoriaRequest request);

    @Override
    CategoriaResponse toResponse(CategoriaEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CategoriaRequest request, @MappingTarget CategoriaEntity entity);

}
