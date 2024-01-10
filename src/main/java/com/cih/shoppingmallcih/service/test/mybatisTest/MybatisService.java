package com.cih.shoppingmallcih.service.test.mybatisTest;


import com.cih.shoppingmallcih.domain.test.guestbook.GuestbookVO;
import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.mappers.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MybatisService {

    private final TimeMapper timeMapper;
    private final ModelMapper modelMapper;

    public String getNow() {
        return timeMapper.getNow();
    }
    public GuestbookDTO getGuestBook() {
        return timeMapper.getGuestBook();
    }

    public void insertGuestBook(GuestbookDTO dto){
        timeMapper.insert(dto);
    }

    public List<GuestbookDTO> getAll(){
        return timeMapper.selectAll().stream()
                .map(vo-> modelMapper.map(vo, GuestbookDTO.class))
                .collect(Collectors.toList());
    }
    public GuestbookDTO getOne(Long gno){

        GuestbookVO guestbookVO = timeMapper.selectOne(gno);

        GuestbookDTO dto = modelMapper.map(guestbookVO, GuestbookDTO.class);
        return dto;
    }
    public void remove(Long gno){
        timeMapper.delete(gno);
    }
    public void modify(GuestbookDTO guestbookDTO){
        GuestbookVO vo = modelMapper.map(guestbookDTO, GuestbookVO.class);
        timeMapper.update(vo);
    }
}
