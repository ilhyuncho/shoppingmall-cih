package com.cih.shoppingmallcih.common;

public class Constants {    // 상수들을 통합 관리하는 클래스

    public enum ExceptionClass {    // 커스텀 예외 클래스에서 메시지 내부에 어떤 도메인에서 문제가 발생했느지 보여주는데 사용
        PRODUCT("Product");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass(){
            return this.exceptionClass;
        }

        public String ToString(){
            return getExceptionClass() + " Exception.";
        }

    }
}
