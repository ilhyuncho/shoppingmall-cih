package com.cih.shoppingmallcih.mappers;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeMapper {
    String getNow();
    GuestbookDTO getGuestBook();
}
