package com.vulenhtho.clothesboot.util;

import java.util.Arrays;

public enum ErrorCode {
    NOT_BANK(Code.NOT_BLANK, "object not blank");
    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code){
        return Arrays
                .stream(values())
                .filter(errorCode -> errorCode.code.equals(code))
                .findFirst()
                .get()
                .message();
    }
    public String message(){
        return message;
    }

    public interface Code{
        String NOT_BLANK = "0012";
    }
}
