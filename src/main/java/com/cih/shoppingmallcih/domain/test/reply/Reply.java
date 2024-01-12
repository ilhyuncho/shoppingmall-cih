package com.cih.shoppingmallcih.domain.test.reply;


import com.cih.shoppingmallcih.domain.common.BaseEntity;
import com.cih.shoppingmallcih.domain.test.board.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="board")
@Table(name = "Reply", indexes = {        // @Table 어노테이션에 인덱스 지정
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")    // board_bno : 실제 db 생성된 컬럼명
})
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;
    private String replyer;

    public void changeText(String text) {
        this.replyText = text;
    }
}
