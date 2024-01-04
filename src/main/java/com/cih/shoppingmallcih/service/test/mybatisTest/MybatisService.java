package com.cih.shoppingmallcih.service.test.mybatisTest;


import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.mappers.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MybatisService {

    private final TimeMapper timeMapper;

    public String getNow() {
        return timeMapper.getNow();
    }
    public GuestbookDTO getGuestBook() {
        return timeMapper.getGuestBook();
    }

}
