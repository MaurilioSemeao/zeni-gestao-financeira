package com.msdev.backend.utils;

import com.msdev.backend.dto.request.ExtratoMensalRequest;
import com.msdev.backend.dto.response.ExtratoMensalResponse;
import com.msdev.backend.entity.ExtratoMensalEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ExtratoMensalMapper extends BaseMapper<ExtratoMensalEntity, ExtratoMensalRequest, ExtratoMensalResponse>{

    @Override
    @Mapping(target = "id", ignore = true)
    ExtratoMensalEntity toEntity(ExtratoMensalRequest request);

    @Override
    ExtratoMensalResponse toResponse(ExtratoMensalEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ExtratoMensalRequest request, @MappingTarget ExtratoMensalEntity entity);
}
