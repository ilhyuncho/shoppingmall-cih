package com.cih.shoppingmallcih.mappers;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.domain.test.guestbook.GuestbookVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeMapper {
    String getNow();
    GuestbookDTO getGuestBook();
    void insert(GuestbookDTO dto);
    List<GuestbookVO> selectAll();
    GuestbookVO selectOne(Long gno);
    void delete(Long gno);
    void update(GuestbookVO vo);

}
