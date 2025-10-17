package com.msdev.backend.enums;

public enum TipoUsuario {

    ADMINISTRADOR(1),
    PADRAO(2);

    private int code;

    private TipoUsuario (int code){
        this.code = code;
    }

    private static TipoUsuario valueOf(int code){
        for(TipoUsuario value: TipoUsuario.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo de Usuário inválido");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
