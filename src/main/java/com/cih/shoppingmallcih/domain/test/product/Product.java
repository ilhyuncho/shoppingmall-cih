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
@EqualsAndHashCode(callSuper = true)    // callSuper : 부모 클래스의 필드를 포함하는 역할을 수행
@ToString(exclude = "name")
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

//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}
