package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;

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

    long count();

   }
