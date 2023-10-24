package com.cih.shoppingmallcih.domain.test.board;


import com.cih.shoppingmallcih.domain.test.guestbook.Guestbook;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

   Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

   Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
