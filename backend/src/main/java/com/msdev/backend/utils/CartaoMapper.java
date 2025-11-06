package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartaoMapper extends BaseMapper<CartaoEntity,CartaoRequest, CartaoResponse> {
    @Override
    @Mapping(target = "id", ignore = true)
    CartaoEntity toEntity(CartaoRequest request);

    @Override
    CartaoResponse toResponse(CartaoEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CartaoRequest request, @MappingTarget CartaoEntity entity);

}
