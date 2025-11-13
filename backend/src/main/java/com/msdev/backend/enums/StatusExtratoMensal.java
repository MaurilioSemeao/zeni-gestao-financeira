package com.msdev.backend.enums;

public enum StatusExtratoMensal {

    ABERTA(1),
    FECHADA(2);

    private int code;
    private StatusExtratoMensal(int conde){
        this.code = conde;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    private static StatusExtratoMensal valueOf(int code){
        for(StatusExtratoMensal value: StatusExtratoMensal.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo De status invalido");
    }

}
