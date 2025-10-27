package com.msdev.backend.utils;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;

public class CartaoMapper {

    public static CartaoEntity toEntity(CartaoRequest request){
        CartaoEntity entity = new CartaoEntity();
        entity.setApelido(request.getApelido());
        entity.setUltimosDigitos(request.getUltimosDigitos());

        return entity;
    }

    public static CartaoResponse toResponse(CartaoEntity entity){
        CartaoResponse response = new CartaoResponse();
        response.setId(entity.getId());
        response.setApelido(entity.getApelido());
        response.setUltimosDigitos(entity.getUltimosDigitos());
        response.setQuantidadeCompras(response.getQuantidadeCompras());
        response.setGastos(entity.getGastos());

        return response;
    }

}
