package com.cih.shoppingmallcih.common.test.actuator.CustomMetric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 커스텀 측정지표 ( 실전 스프링 부트 p204 )
// http://localhost:8090/actuator/metrics/api.courses.created.count
@Configuration
public class CourseMetricsConfiguration {
    // 커스텀 측정지표 ( 카운터, 게이지, 타이머, 분포요약 ..) 중 카운터 지표 측정
    @Bean       // Counter 빈 새성
    public Counter createCourseCounter(MeterRegistry meterRegistry) {
        return Counter.builder("api.courses.created.count")
                .description("Total nuber of couses")
                .register(meterRegistry);       // MeterRegistry에 등록
    }
}
