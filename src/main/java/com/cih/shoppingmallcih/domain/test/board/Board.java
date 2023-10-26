package com.cih.shoppingmallcih.domain.test.board;

import com.cih.shoppingmallcih.domain.common.BaseEntity;
import com.cih.shoppingmallcih.domain.test.boardImage.BoardImage;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @OneToMany(mappedBy = "board",      // BoardImage의 board변수
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
        )
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }
    public void clearImage(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
