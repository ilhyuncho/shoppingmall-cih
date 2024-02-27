package com.cih.shoppingmallcih.dto.test.sample;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HotelRoomResponse {

    @JsonProperty("id") // JSON 객체로 변경시 속성값을 id 로 변경
    @JsonSerialize(using = ToStringSerializer.class)    // hotelRoomId 변수의 Long값을 String 타입으로 변경
    private final Long hotelRoomId;

    private final String roomNumber;

    private final HotelRoomType hotelRoomType;

    @JsonSerialize(using = ToDollarStringSerializer.class)  // ToDollarStringSerializer 사용자 정의 구현체
                                                            // BigDecimal 값을 달러 표기 형태로 변환
    private final BigDecimal originalPrice;

    private final List<Reservation> reservations;

    private HotelRoomResponse(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelRoomType = hotelRoomType;
        this.originalPrice = originalPrice;
        reservations = new ArrayList<>();
    }

    public static HotelRoomResponse of(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        return new HotelRoomResponse(hotelRoomId, roomNumber, hotelRoomType, originalPrice);
    }

    public void reservedAt(LocalDate reservedAt) {
        reservations.add(new Reservation(IdGenerator.create(), reservedAt));
    }

    @Getter
    private static class Reservation {

        @JsonProperty("id")
        @JsonSerialize(using = ToStringSerializer.class)    //
        private final Long reservationId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // 마셜링 과정에서 LocalDate 타입을
                                                                             // 사용자 정의 포멧으로 변경하기 위해
        private final LocalDate reservedDate;

        public Reservation(Long reservationId, LocalDate reservedDate) {
            this.reservationId = reservationId;
            this.reservedDate = reservedDate;
        }
    }
}
