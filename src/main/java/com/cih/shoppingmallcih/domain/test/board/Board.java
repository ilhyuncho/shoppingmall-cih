package com.cih.shoppingmallcih.domain.test.board;

import com.cih.shoppingmallcih.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

//    @OneToMany(mappedBy = "board",
//            cascade = {CascadeType.ALL},
//            fetch = FetchType.LAZY,
//            orphanRemoval = true)
//    @Builder.Default
//    private Set<BoardImage> imageSet = new HashSet<>();
//
//    public void addImage(String uuid, String fileName){
//        BoardImage boardImage = BoardImage.builder()
//                .uuid(uuid)
//                .fileName(fileName)
//                .board(this)
//                .ord(imageSet.size())
//                .build();
//        imageSet.add(boardImage);
//    }
//    public void clearImages(){
//        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
//    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
