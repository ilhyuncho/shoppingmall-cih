package com.cih.shoppingmallcih.common.test.actuator.CustomMetric;

import com.cih.shoppingmallcih.service.test.CourceService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 커스텀 측정지표 ( 실전 스프링 부트 p204 )

@Configuration
public class CourseMetricsConfiguration {

    // http://localhost:8090/actuator/metrics/api.courses.created.count
    // 커스텀 측정지표 ( 카운터, 게이지, 타이머, 분포요약 ..) 중 카운터 지표 측정
    @Bean       // Counter 빈 새성
    public Counter createCourseCounter(MeterRegistry meterRegistry) {
        return Counter.builder("api.courses.created.count")
                .description("Total nuber of couses")
                .register(meterRegistry);       // MeterRegistry에 등록
    }

    //http://localhost:8090/actuator/metrics/api.courses.created.gauge
    @Bean
    public Gauge createCoursesGauge(MeterRegistry meterRegistry, CourceService courceService) {
        // Counter의 단점은 서버 종료시 초기화 된다는 것
        // Gauge 는 db를 통해 값을 저장하고 Gauge를 통해 누적된 값을 확인 가능
        // db에서 읽어오므로 서비스 메서드와 통합할 필요가 없다
        return Gauge.builder("api.courses.created.gauge", courceService::count) // 생성된 갯수를 db에서 가져옴
                .description("Total number2 of couses")
                .register(meterRegistry);       // MeterRegistry에 등록
    }
}












