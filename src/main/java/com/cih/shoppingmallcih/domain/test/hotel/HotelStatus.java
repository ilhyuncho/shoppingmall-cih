package com.cih.shoppingmallcih.domain.test.hotel;

public enum HotelStatus {
    OPEN(1), CLOSED(-1), READY(0);


    private Integer value;  // db의 값으로 변환할때 사용할 열거형의 속성

    HotelStatus(Integer value) {
        this.value = value;
    }

}
