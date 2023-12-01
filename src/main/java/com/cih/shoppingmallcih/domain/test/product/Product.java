package com.cih.shoppingmallcih.domain.test.product;


import com.cih.shoppingmallcih.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude="productDetail")    // callSuper : 부모 클래스의 필드를 포함하는 역할을 수행
@ToString(callSuper = true)
@Table(name="product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @OneToOne(mappedBy = "product") // mappedBy는 어떤 객체가 주인인건지.. Product는 주인이 아니다
        // 양방향으로 연관관계가 설정되면 ToString을 사용할 떄 순환참조가 발생하기 떄문에
    @ToString.Exclude
    private ProductDetail productDetail;

}
