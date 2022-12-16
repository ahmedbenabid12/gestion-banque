package com.gestionbanque.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BanqueException extends Exception {

    public static final String ID_NOT_FOUND = "ID NON TROUVE";
    public static final String BAD_REQUEST = "BAD REQUEST";
    public static final String CLIENT_EXISTE = "CLIENT EXISTE DEJA";

    private final String code;
    private final String message;

    public BanqueException(Type type, String message) {
        //super(type.getMessage());
        this.code = type.getCode();
        this.message = message;
    }

    @Getter
    @AllArgsConstructor
    public enum Type {

        E001("E001", ID_NOT_FOUND ),
        E002("E002", BAD_REQUEST),
        E003("E003", CLIENT_EXISTE);

        private final String code;
        private final String message;
    }


}
