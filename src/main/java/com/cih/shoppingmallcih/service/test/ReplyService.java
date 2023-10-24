package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.dto.test.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
}

