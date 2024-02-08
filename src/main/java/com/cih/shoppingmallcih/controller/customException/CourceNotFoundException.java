package com.cih.shoppingmallcih.controller.customException;


// RestFul 예외 처리 1: (1) 커스텀 예외 정의
public class CourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 4540534534L;

    public CourceNotFoundException(String message){
        // 존재하지 않는 과정(ID)에 대해 API사용자가 접근을 시도할때
        super(message);
    }
}
