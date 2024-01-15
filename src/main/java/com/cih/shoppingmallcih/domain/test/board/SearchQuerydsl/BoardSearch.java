package com.cih.shoppingmallcih.domain.test.board.SearchQuerydsl;


import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.dto.test.BoardListAllDTO;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Querydsl(1)을 이용할 인터페이스 선언
public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

   Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

   Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

   //Page<BoardListReplyCountDTO> searchWithAll(String[] tyeps, String keyword, Pageable pageable);
    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
}
