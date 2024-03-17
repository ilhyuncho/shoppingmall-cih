package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.domain.test.guestbook.Guestbook;
import com.cih.shoppingmallcih.domain.test.guestbook.GuestbookRepository;
import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.dto.typeCommand.GuestBookType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService{

    private final ModelMapper modelMapper;

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto)
    {
        log.info(dto);

        //Guestbook guestbook = dtoToEntity(dto);   // 직접구현 방식(구)
        Guestbook guestbook =modelMapper.map(dto, Guestbook.class);

        guestbookRepository.save(guestbook);

        log.info(guestbook);

        return guestbook.getGno();
    }

    @Override
    public GuestbookDTO readOne(Long gno) {
        Optional<Guestbook> result = guestbookRepository.findById(gno);

        Guestbook guestbook = result.orElseThrow();

        GuestbookDTO dto = modelMapper.map(guestbook, GuestbookDTO.class);
        return dto;

    }

    @Override
    public void modify(GuestbookDTO guestbookDTO) {

        Optional<Guestbook> result = guestbookRepository.findById(guestbookDTO.getGno());

        Guestbook guestbook = result.orElseThrow();

        guestbook.change(guestbookDTO.getTitle(), guestbookDTO.getContent());

        guestbookRepository.save(guestbook);
    }

    @Override
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }

    public void testUserType(String type){

        log.error("testUserType() : String: " + type);
    }
    public void testUserTypeNew(GuestBookType type){
        log.error("testUserTypeNew() GuestBookType: " + type);
    }



}
