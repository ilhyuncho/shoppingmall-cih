package com.cih.shoppingmallcih.domain.test.board.SearchQuerydsl;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.board.QBoard;
import com.cih.shoppingmallcih.domain.test.reply.QReply;
import com.cih.shoppingmallcih.dto.test.BoardImageDTO;
import com.cih.shoppingmallcih.dto.test.BoardListAllDTO;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

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

//    @Override
//    public Page<BoardListReplyCountDTO> searchWithAll(String[] tyeps, String keyword, Pageable pageable) {
//        // Querydsl을 이용
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//
//        JPQLQuery<Board> boardJPQLQuery = from(board);
//        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));
//
//        getQuerydsl().applyPagination(pageable, boardJPQLQuery);    // paging
//
//        List<Board> boardList = boardJPQLQuery.fetch();
//
//        boardList.forEach(board1 -> {
//            System.out.println(board1.getBno());
//            System.out.println(board1.getImageSet());
//            System.out.println("---------------");
//        });
//
//        return null;
//    }

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> boardJPQLQuery = from(board);
        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));

        if((types!=null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){
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
            boardJPQLQuery.where(booleanBuilder);
        }


        boardJPQLQuery.groupBy(board);

        getQuerydsl().applyPagination(pageable, boardJPQLQuery); // paging

        // 임시로 데이터를 튜플 타입으로 추출해서 처리
        JPQLQuery<Tuple> tupleJPQLQuery = boardJPQLQuery.select(board, reply.countDistinct());

        List<Tuple> tupleList = tupleJPQLQuery.fetch();
        // List<Tuple> 이용하는 방식은 Projections를 이용하는 방식보다 번거롭기는 하지만,
        // 코드를 통해서 마음대로 커스터마이징 할수 있다는 장점이 있다.

        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {
            Board board1 = (Board) tuple.get(board);
            Long replyCount = tuple.get(1, Long.class);

            BoardListAllDTO dto = BoardListAllDTO.builder()
                    .bno(board1.getBno())
                    .title(board1.getTitle())
                    .writer(board1.getWriter())
                    .regDate(board1.getRegDate())
                    .replyCount(replyCount)
                    .build();

            // BoardImage를 BoardImageDTO 처리할 부분
            List<BoardImageDTO> imageDTOS = board1.getImageSet().stream().sorted()
                    .map(boardImage -> BoardImageDTO.builder()
                            .uuid(boardImage.getUuid())
                            .filename(boardImage.getFileName())
                            .ord(boardImage.getOrd())
                            .build()
                    ).collect(Collectors.toList());
            dto.setBoardImages(imageDTOS);

            return dto;
        }).collect(Collectors.toList());

        Long totalCount = boardJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, totalCount);
    }
}
