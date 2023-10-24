package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.reply.Reply;
import com.cih.shoppingmallcih.domain.test.reply.ReplyRepository;
import com.cih.shoppingmallcih.dto.test.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);

        log.info(reply.getBoard()); // null

//        replyRepository.save(reply).getRno();
        replyRepository.save(reply);
        return reply.getRno();
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
        Optional<Reply> byId = replyRepository.findById(rno);

        if(byId.isPresent()){
            replyRepository.deleteById(rno);
        }

    }
}
