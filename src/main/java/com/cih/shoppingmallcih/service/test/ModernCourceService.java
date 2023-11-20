package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModernCourceService {

    private CourceRepository courceRepository;

    @Autowired
    public ModernCourceService(CourceRepository courceRepository) {
        this.courceRepository = courceRepository;
    }

//    Iterable<Cource> getAvailableCources(){};
//
//    Cource createCource(Cource cource){};
//
//    Optional<Cource> getCourceById(Long courceId){};
//
//    Iterable<Cource> getCourcesByCategory(String category){};
//
//    Iterable<Cource> getCources(){};
//
//    Cource updateCource(Long courceId, Cource cource){};

    public Iterable<Cource> getAvailableCources() {
        return courceRepository.findAll();
    }

    void deleteCourceById(Long courceId){};

    void deleteCources(){};


}