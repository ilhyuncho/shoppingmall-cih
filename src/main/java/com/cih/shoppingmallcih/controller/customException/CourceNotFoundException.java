package com.cih.shoppingmallcih.controller.customException;

public class CourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 4540534534L;

    public CourceNotFoundException(String message){
        // 존재하지 않는 과정에 대해 API사용자가 접근을 시도할때

        super(message);
    }

}
