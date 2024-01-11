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
//접근 권한을 private가 아니라 protected로 적용함으로써 프록시 객체를 생성하게 해줄 수 있기 때문이다.
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
