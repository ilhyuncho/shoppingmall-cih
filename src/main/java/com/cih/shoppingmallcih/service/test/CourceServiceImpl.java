package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.controller.customException.CourceNotFoundException;
import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourceServiceImpl implements CourceService{
    private CourceRepository courceRepository;

    private final Counter createCourseCounter;

    @Autowired
    public CourceServiceImpl(CourceRepository courceRepository, Counter counter) {
        // 직접 주입 받아 사용하면 측정지표 코드와 비즈니스 로직을 강하게 결합시키므로 좋은 방법이 아니다.
        // 대신 스프링 의 이벤트 리스너를 사용하면 Counter를 비즈니스 로직으로부터 분리할수 있다.
        this.courceRepository = courceRepository;
        this.createCourseCounter = counter;
    }

    public Iterable<Cource> getAvailableCources() {
        return courceRepository.findAll();
    }

    @Override
    public Cource createCource(Cource cource) {
        // 호출 될때마다 Counter를 증가 시킴
        createCourseCounter.increment();

        return courceRepository.save(cource);
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
}
