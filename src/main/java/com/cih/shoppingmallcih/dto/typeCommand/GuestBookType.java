package com.cih.shoppingmallcih.dto.typeCommand;

public class GuestBookType {

    // 분류 타입을 클래스로 치환
    // 프로그래머의 실수를 컴파일러가 검출 가능
    private final String _type;

    public GuestBookType(String type) {
        _type = type;
    }

    public String toString(){
        return "[ GuestBook Type:" + _type + " ]";
    }
}
