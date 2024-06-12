package viettel.gpmn.platform.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityWithAware extends EntityWithULID {
    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedBy
    private String updatedBy;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private DBStatus status = DBStatus.ACTIVE;
}
