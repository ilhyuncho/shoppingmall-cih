package com.cih.shoppingmallcih.mybatis;

import com.cih.shoppingmallcih.domain.test.guestbook.GuestbookVO;
import com.cih.shoppingmallcih.mappers.TimeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class GuestbookMapperTests {

    @Autowired
    private TimeMapper timeMapper;
    @Test
    public void testSelectAll(){
        List<GuestbookVO> list = timeMapper.selectAll();
        list.forEach(log::info);
    }
    @Test
    public void testSelectOne(){
        GuestbookVO guestbookVO = timeMapper.selectOne(1L);
        log.info(guestbookVO);
    }
}
