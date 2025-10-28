package com.msdev.backend.utils;

import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(UsuarioEntity entity){
        UsuarioResponse response = new UsuarioResponse();
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        response.setTipoUsuario(entity.getTipoUsuario());

        return  response;
    }

    public static UsuarioEntity toEntity(UsuarioRequest request){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setSenha(request.getSenha());
        return entity;
    }


}
