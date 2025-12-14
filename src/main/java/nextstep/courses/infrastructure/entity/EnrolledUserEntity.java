package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class EnrolledUserEntity {
    
    private final Long enrollmentId;
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    
    public EnrolledUserEntity(Long enrollmentId, Long id, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.enrollmentId = enrollmentId;
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    public Long getEnrollmentId() {
        return enrollmentId;
    }
    
    public Long getId() {
        return id;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
