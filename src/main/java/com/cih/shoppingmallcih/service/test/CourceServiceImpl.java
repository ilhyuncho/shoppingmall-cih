package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
