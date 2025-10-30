package com.msdev.backend.utils;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.entity.TransacaoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    @Mapping(target = "id", ignore = true)
    TransacaoEntity toEntity(TransacaoRequest request);

    TransacaoResponse toResponse(TransacaoEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaTransacoes(TransacaoRequest request, @MappingTarget TransacaoEntity entity);
}
