package com.msdev.backend.utils;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.entity.TransacaoEntity;

public class TransacaoMapper {

    public static TransacaoEntity toEntity(TransacaoRequest request){
        TransacaoEntity entity = new TransacaoEntity();
        entity.setDescricao(request.getDescricao());
        entity.setValor(request.getValor());
        entity.setTipo(request.getTipo());
        entity.setPrevisao(request.isPrevisao());
        entity.setMeioPagamento(request.getMeioPagamento());
        return  entity;
    }

    public static TransacaoResponse toResponse(TransacaoEntity entity){
        TransacaoResponse response = new TransacaoResponse();
        response.setId(entity.getId());
        response.setDescricao(entity.getDescricao());
        response.setValor(entity.getValor());
        response.setTipo(entity.getTipo());
        response.setDataTransacao(entity.getDataTransacao());
        response.setPrevisao(entity.isPrevisao());

        response.setMeioPagamento(entity.getMeioPagamento());

        return response;
    }
}
