package com.cih.shoppingmallcih.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass   // JPA의 엔티티 클래스가 상속받을 경우 자식 클래스에서 매핑 정보를 전달
@EntityListeners(value = { AuditingEntityListener.class})   // 해당 클래스에 Auditing 기능을 포함
            // 엔티티를 db에 적용하기 전후로 콜백을 요청할 수 있게 하는 어노테이션
@Getter
public abstract class BaseEntity {

    @CreatedDate    // JPA에서 엔티티의 생성 시간을 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
