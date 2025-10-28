package com.msdev.backend.enums;

public enum TipoTransacao {
    RECEITA(1),
    DESPESA(2);

    private int code;

    private TipoTransacao(int conde){
        this.code = conde;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private static TipoTransacao valueOf(int code){
        for(TipoTransacao value: TipoTransacao.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo Transação inválido");
    }
}
