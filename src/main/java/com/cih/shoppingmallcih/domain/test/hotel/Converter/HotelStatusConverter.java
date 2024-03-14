package com.cih.shoppingmallcih.domain.test.hotel.Converter;

import com.cih.shoppingmallcih.domain.test.hotel.HotelStatus;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class HotelStatusConverter implements AttributeConverter<HotelStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HotelStatus attribute) {
        if(Objects.isNull(attribute))
            return null;

        return attribute.getValue();
    }

    @Override
    public HotelStatus convertToEntityAttribute(Integer dbData) {
        if(Objects.isNull(dbData)){
            return null;
        }
        return HotelStatus.fromValue(dbData);
    }
}
