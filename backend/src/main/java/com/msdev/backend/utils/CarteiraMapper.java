package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.response.CarteiraResponse;
import com.msdev.backend.entity.CarteiraEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarteiraMapper {

    @Mapping(target = "id", ignore = true)
    CarteiraEntity toEntity(CarteiraRequest request);

    CarteiraResponse toResponse(CarteiraEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaCarteira(CarteiraRequest request, @MappingTarget CarteiraEntity entity);
}
