package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.reply.Reply;
import com.cih.shoppingmallcih.domain.test.reply.ReplyRepository;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.dto.test.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {

        //이부분 때문에 ModelMapper설정- MatchingStrategies.LOOSE 으로 수정
       Reply reply = modelMapper.map(replyDTO, Reply.class);


        log.info(reply.getBoard()); // null

        // db에 생성된 RNO 값 반환
        return replyRepository.save(reply).getRno();
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);

        Reply reply = result.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());

        Reply reply = result.orElseThrow();

        reply.changeText(replyDTO.getReplyText());  // 댓글의 내용만 수정 가능

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {

        // 이 부분 필요한 부분인가??
        Optional<Reply> byId = replyRepository.findById(rno);
        if(byId.isPresent()){

            replyRepository.deleteById(rno);
        }
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() -1,
        pageRequestDTO.getSize(), Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().
                map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> pageResponseDTO = PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

        return pageResponseDTO;
    }
}
