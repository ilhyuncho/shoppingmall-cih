package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import com.cih.shoppingmallcih.dto.test.Validation.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface CourceService {

    Iterable<Cource> getAvailableCources();

    Cource createCource(Cource cource);

    Optional<Cource> getCourceById(Long courceId);

    Iterable<Cource> getCourcesByCategory(String category);

    Iterable<Cource> getCources();

    Cource updateCource(Long courceId, Cource cource);

    void deleteCourceById(Long courceId);

    void deleteCources();


   }
