package com.cih.shoppingmallcih.common.test.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)  // 이 어노테이션을 어디서 선언할 수 있는지 정의하는데 사용
@Retention(RetentionPolicy.RUNTIME) // 이 어노테이셔니 실제로 적용되고 유지되는 범위를 의미 함
@Constraint(validatedBy =  TelephoneValidator.class)    // TelepohoneValidator와 매핑하는 작업을 수행
public @interface Telephone {   //Telephone 어노테이션 인터페이스
    String message() default "전화번호 형식이 일치하지 않습니다."; // 유효성 검사가 실패할 경우 반환되는 메시지
    Class[] groups() default  {};                             // 유효성 검사를 사용하는 그룹으로 설정 함
    Class[] payload() default {};                             // 사용자가 추가 정보를 위해 전달하는 값


}
