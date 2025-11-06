package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.response.CarteiraResponse;
import com.msdev.backend.entity.CarteiraEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarteiraMapper extends BaseMapper<CarteiraEntity,CarteiraRequest, CarteiraResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    CarteiraEntity toEntity(CarteiraRequest request);

    @Override
    CarteiraResponse toResponse(CarteiraEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CarteiraRequest request, @MappingTarget CarteiraEntity entity);
}
