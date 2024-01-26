package com.cih.shoppingmallcih.common.test.validator;



import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j2
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {
    //<1,2> : 첫번째 인자는 커스텀 밸리데이터 로직을 적용하게 해주는 애너테이션
    //        두번째 인자는 커스텀 애너테이션을 적용해야 하는 데이터 타입

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        log.error("TelephoneValidator isValid : " + value);

        return value.matches("01(?:0|1|[6-9])[.-]~?(\\d{3}|\\d{4})[.-]?(\\d{4})$");
        // false가 리턴되면 MethodArgumentNotValidException 예외가 발생함.
    }
}
