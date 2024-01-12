package com.cih.shoppingmallcih.domain.test.board.SearchQuerydsl;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.board.QBoard;
import com.cih.shoppingmallcih.domain.test.reply.QReply;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

//Querydsl
//Querydsl(2) - 반드시 '인터페이스 이름 + impl'로
// QuerydslRepositorySupport 를 부모 클래스로 지정,
// BoardSearch 인터페이스 구현
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }   // for QuerydslRepositorySupport

//    public boardSearchImpl(Class<?> domainClass) {
//        super(domainClass);
//    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        // JPQLQuery 사용법  ( @QUery로 작성했던 JPQL을 코드를 통해서 생성 할수 있다, )

        QBoard board = QBoard.board;    // Q도메인 객체

        // 2.JPQLQuery 를 이용
        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        // paging 처리
        // BoardSearchImpl이 상속한 QuerydslRepositorySupport 라는 클래스의 기능을 이용
        this.getQuerydsl().applyPagination(pageable, query);
        // paging 처리



        List<Board> list = query.fetch();   // 쿼리 실행
        long count = query.fetchCount();    // count 쿼리

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length >0) && keyword != null){ // 검색 조건과 키워드가 있다면
            // Querydsl을 이용할 때 조건 처리에서 '( )'가 필요한 상황이라면 BooleanBuilder 를 이용
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }   // end if

        query.where(board.bno.gt(0));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        // PageImpl클래스를 제공해서 3개의 파라미터로 Page<T>를 생성할 수 있다.
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        // leftJoin 처리
        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);   // 조인 처리 후에 게시물당 처리가 필요해서


        if( (types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){
                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        // JPA에서는 Projection(프로젝션) 이라고 해서 JPQL의 결과를 바로 DTO로 처리하는 기능
        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.bno,
                board.title,
                board.writer,
                board.regDate,
                reply.count().as("replyCount")
        ));

        // paging
        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}
