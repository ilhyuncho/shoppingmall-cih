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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@NoArgsConstructor
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

    @OneToMany(mappedBy = "board",
            cascade = {CascadeType.ALL},    // 영속성 전이 제어를 위해 ( 상위 엔티티가 영속처리 될떄 하위 엔티티 영속처리 방법)
            fetch = FetchType.LAZY,
            orphanRemoval = true)   // orphanRemoval = true -> 부모 엔티티가 자식 엔티티와의 관계를 제거하면
                                    // 자식은 고아로 취급되어 그대로 사라진다.
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)            // board에 대한 참조를 this 로
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }
    public void clearImages(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
