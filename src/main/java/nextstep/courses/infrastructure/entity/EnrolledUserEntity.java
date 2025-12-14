package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class EnrolledUserEntity {
    
    private final Long enrollmentId;
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    
    public EnrolledUserEntity(Long enrollmentId, Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.enrollmentId = enrollmentId;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Long getEnrollmentId() {
        return enrollmentId;
    }
    
    public Long getId() {
        return id;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
