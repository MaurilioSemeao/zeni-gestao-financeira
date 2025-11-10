package com.msdev.backend.enums;

public enum MeioPagamento {


    DEBITO(1),
    CREDITO(2),
    DINHEIRO(3),
    PIX(4);

    private int code;

    private MeioPagamento(int conde){
        this.code = conde;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private static MeioPagamento valueOf(int code){
        for(MeioPagamento value: MeioPagamento.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo Transação inválido");
    }
}
