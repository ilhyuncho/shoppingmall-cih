package com.cih.shoppingmallcih.domain.test.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

//    public boardSearchImpl(Class<?> domainClass) {
//        super(domainClass);
//    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        // JPQLQuery 사용법  ( @QUery로 작성했던 JPQL을 코드를 통해서 생성 할수 있다, )

        QBoard board = QBoard.board;    // Q도메인 객체

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();   // 쿼리 실행
        Long count = query.fetchCount();    // count 쿼리 

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length >0) && keyword != null){
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
}
