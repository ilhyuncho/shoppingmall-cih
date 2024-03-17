package com.cih.shoppingmallcih.dto.typeCommand;

public class GuestBookType {

    // 분류 타입을 클래스로 치환
    // 프로그래머의 실수를 컴파일러가 검출 가능

    public static final GuestBookType GUEST_GOLD_NEW = new GuestBookType("GOLD");
    public static final GuestBookType GUEST_SILVER_NEW = new GuestBookType("SILVER");
    public static final GuestBookType GUEST_BRONZE_NEW = new GuestBookType("BRONZE");

    private final String _type;

    public GuestBookType(String type) {
        _type = type;
    }

    public String toString(){
        return "[ GuestBook Type:" + _type + " ]";
    }
}
