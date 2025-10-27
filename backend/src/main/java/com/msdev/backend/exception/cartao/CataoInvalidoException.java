package com.msdev.backend.exception.cartao;

import com.msdev.backend.exception.BusinessException;

public class CataoInvalidoException extends BusinessException {

    public CataoInvalidoException(String message){
        super(message);
    }
}

