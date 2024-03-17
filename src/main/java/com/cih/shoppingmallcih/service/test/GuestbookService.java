package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.dto.typeCommand.GuestBookType;


public interface GuestbookService {

    Long register(GuestbookDTO dto);
    GuestbookDTO readOne(Long gno);

    void modify(GuestbookDTO boardDTO);

    void remove(Long gno);


    // ModelMapper 직접 생성 ( modelmapper 대체 )
//    default Guestbook dtoToEntity(GuestbookDTO dto){
//        Guestbook guestbook = Guestbook.builder()
//                .gno(dto.getGno())
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .writer(dto.getWriter())
//                .build();
//        return guestbook;
//    }

    void testUserType(String type);
    void testUserTypeNew(GuestBookType type);


}
