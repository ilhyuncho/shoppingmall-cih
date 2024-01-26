package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.controller.customException.CourceNotFoundException;
import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourceServiceImpl implements CourceService{
    private CourceRepository courceRepository;

    @Autowired
    public CourceServiceImpl(CourceRepository courceRepository) {
        this.courceRepository = courceRepository;
    }

    public Iterable<Cource> getAvailableCources() {
        return courceRepository.findAll();
    }

    @Override
    public Cource createCource(Cource cource) {
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
