package com.cih.shoppingmallcih.domain.test.hotel;


import com.cih.shoppingmallcih.domain.test.hotel.Converter.HotelStatusConverter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "hotels", indexes = @Index(name = "INDEX_NAME_STATUS", columnList = "name asc, status asc"))
public class HotelEntity extends AbstractManageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "status")
   // @Enumerated(value=EnumType.STRING)  // status를 변환하는 방식, DB저장시 문자열(키?)로 저장, EnumType.ORDINAL이면 숫자
    @Convert(converter= HotelStatusConverter.class)
    private HotelStatus status;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "room_count")
    private Integer roomCount;

    public HotelEntity(){
        super();
    }
}
