package com.cih.shoppingmallcih.dto.test;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)  // 기존에 구성한 빌더를 기반으로 새로운 객체를 재 구성 할 수 있도록 도와주는 속성입니다.
public class SampleDTO {

    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
