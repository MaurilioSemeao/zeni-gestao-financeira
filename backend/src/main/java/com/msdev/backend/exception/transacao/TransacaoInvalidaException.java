package com.msdev.backend.exception.transacao;

import com.msdev.backend.exception.BusinessException;

public class TransacaoInvalidaException extends BusinessException {


    public TransacaoInvalidaException(String message) {
        super(message);
    }
}
