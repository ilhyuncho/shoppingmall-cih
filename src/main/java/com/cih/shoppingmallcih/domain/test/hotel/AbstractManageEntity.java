package com.cih.shoppingmallcih.domain.test.hotel;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
public abstract class AbstractManageEntity {
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
   // @Temporal(value= TemporalType.TIME)
    //  @Temporal should only be set on a java.util.Date or java.util.Calendar property
    private ZonedDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public AbstractManageEntity() {
        this.createdAt = ZonedDateTime.now();
        this.createdBy = UserIdHolder.getUserId();
    }
}
