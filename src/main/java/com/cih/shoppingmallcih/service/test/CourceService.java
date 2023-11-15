package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import com.cih.shoppingmallcih.dto.test.Validation.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface CourceService {

    public Iterable<Cource> getAvailableCources();

   }
