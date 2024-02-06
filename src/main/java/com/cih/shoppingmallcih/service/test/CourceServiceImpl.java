package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.controller.customException.CourceNotFoundException;
import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourceServiceImpl implements CourceService{
    private CourceRepository courceRepository;

    private final Counter createCourseCounter;
    private final Timer createCourseTimer;

    @Autowired
    public CourceServiceImpl(CourceRepository courceRepository, Counter counter, Timer timer) {
        // 직접 주입 받아 사용하면 측정지표 코드와 비즈니스 로직을 강하게 결합시키므로 좋은 방법이 아니다.
        // 대신 스프링 의 이벤트 리스너를 사용하면 Counter를 비즈니스 로직으로부터 분리할수 있다.
        this.courceRepository = courceRepository;
        this.createCourseCounter = counter;
        this.createCourseTimer = timer;
    }

    public Iterable<Cource> getAvailableCources() {
        return courceRepository.findAll();
    }

    @Override
    @SneakyThrows   // Java에서 메서드 선언부에 Throws 를 정의하지 않고도, 검사 된 예외를 Throw 할 수 있도록 하는
                    // Lombok 에서 제공하는 어노테이션입니다.
                    // 즉, throws 나 try-catch 구문을 통해서 Exception 에 대해 번거롭게 명시적으로 예외 처리를
                    // 해줘야 하는 경우에 @SneakyThrows 어노테이션을 사용하여 명시적인 예외 처리를 생략할 수 있습니다.
    public Cource createCource(Cource cource) {
        // 호출 될때마다 Counter를 증가 시킴
        createCourseCounter.increment();

        //return courceRepository.save(cource);
        return createCourseTimer.recordCallable(    // db에 값을 저장하고 값을 반환하는 Callable객체를 람다식으로 정의
                () -> courceRepository.save(cource)
        );
    }

    @Override
    public Optional<Cource> getCourceById(Long courceId) {
        return courceRepository.findById(courceId);
    }

    @Override
    public Iterable<Cource> getCourcesByCategory(String category) {
        return courceRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Cource> getCources() {
        return courceRepository.findAll();
    }

    @Override
    public Cource updateCource(Long courceId, Cource cource) {
//        courceRepository.findById(courceId).ifPresent(dbCource -> {
//            dbCource.setName(cource.getName());
//            dbCource.setCategory(cource.getCategory());
//            dbCource.setDescription(cource.getDescription());
//            dbCource.setRating(cource.getRating());
//            courceRepository.save(dbCource);
//        });

        Cource existingCource = courceRepository.findById(courceId)
                // CourceNotFoundException 발생 !!!!!!!!!!!!!!!!
                .orElseThrow(() ->new CourceNotFoundException(
                        String.format("No cource with id %s is available", courceId)
                ));

        existingCource.setName(cource.getName());
        existingCource.setCategory(cource.getCategory());
        existingCource.setDescription(cource.getDescription());
        existingCource.setRating(cource.getRating());

        return courceRepository.save(existingCource);
    }

    @Override
    public void deleteCourceById(Long courceId) {
        // CourceNotFoundException 발생 !!!!!!!!!!!!!!!!
        courceRepository.findById(courceId).orElseThrow(() -> new CourceNotFoundException(
                String.format("No cource with id %s is available", courceId)
        ));
       courceRepository.deleteById(courceId);
    }

    @Override
    public void deleteCources() {
        courceRepository.deleteAll();
    }
    @Override
    public long count() {
        return courceRepository.count();
    }
}
