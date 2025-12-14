package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.enumerate.EnrollmentType;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class EnrollmentEntity {
    
    private final Long sessionId;
    private final Long id;
    private final EnrollmentType type;
    private final long tuitionFee;
    private final int maxEnrollment;
    private final SessionStatusType sessionStatus;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    
    public EnrollmentEntity(Long sessionId, Long id, EnrollmentType type, long tuitionFee, int maxEnrollment, SessionStatusType sessionStatus, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.sessionId = sessionId;
        this.id = id;
        this.type = type;
        this.tuitionFee = tuitionFee;
        this.maxEnrollment = maxEnrollment;
        this.sessionStatus = sessionStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    public Long getSessionId() {
        return sessionId;
    }
    
    public Long getId() {
        return id;
    }
    
    public EnrollmentType getType() {
        return type;
    }
    
    public long getTuitionFee() {
        return tuitionFee;
    }
    
    public int getMaxEnrollment() {
        return maxEnrollment;
    }
    
    public SessionStatusType getSessionStatus() {
        return sessionStatus;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
