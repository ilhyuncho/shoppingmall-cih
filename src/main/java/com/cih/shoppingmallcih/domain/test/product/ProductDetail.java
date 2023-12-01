package com.cih.shoppingmallcih.domain.test.product;

import com.cih.shoppingmallcih.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne   // 기본 fetch 전략은 EAGER( 즉시 로딩 전략 )
                // @OneToOne의 optional() 메서드의 기본값으로 true가 설정됨. -> 매핑 되는 값이 nullable이라는 것을 의미
                // 그래서 값 가져올때 Product테이블을 left otuer join으로 실행
                // @OneToOne(optional = false) 로 지정하면 inner join으로 실행 됨. null값을 허용하지 않기 떄문에
    @JoinColumn(name="product_number") // @JoinColumn을 선언하지 않으면 중간 테이블이 생김
    private Product product;


}
