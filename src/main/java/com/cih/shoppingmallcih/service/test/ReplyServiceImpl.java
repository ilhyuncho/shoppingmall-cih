package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.reply.Reply;
import com.cih.shoppingmallcih.domain.test.reply.ReplyRepository;
import com.cih.shoppingmallcih.dto.test.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
