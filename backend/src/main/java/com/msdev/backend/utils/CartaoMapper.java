package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    @Mapping(target = "id", ignore = true)
    CartaoEntity toEntity(CartaoRequest request);

    CartaoResponse toResponse(CartaoEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaCartao(CartaoRequest request, @MappingTarget CartaoEntity entity);

}
