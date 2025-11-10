package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CategoriaRequest;
import com.msdev.backend.dto.response.CategoriaResponse;
import com.msdev.backend.entity.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<CategoriaEntity, CategoriaRequest, CategoriaResponse> {

}
