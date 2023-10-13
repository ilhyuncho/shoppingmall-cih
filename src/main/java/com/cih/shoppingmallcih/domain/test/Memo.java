package com.cih.shoppingmallcih.domain.test;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 무분별한 객체 생성을 막는다
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
