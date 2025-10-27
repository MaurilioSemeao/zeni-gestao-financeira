package com.msdev.backend.exception.usuario;

import com.msdev.backend.exception.BusinessException;

public class UsuarioInvalidoException extends BusinessException {

    public UsuarioInvalidoException(String message){
        super(message);
    }
}
