package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class EnrollmentEntity {
    
    private final Long sessionId;
    private final Long id;
    private final String type;
    private final long tuitionFee;
    private final int maxEnrollment;
    private final String progressStatus;
    private final String recruitmentStatus;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public EnrollmentEntity(Long sessionId, Long id, String type, long tuitionFee, int maxEnrollment, String progressStatus, String recruitmentStatus, LocalDateTime createdDate,
        LocalDateTime updatedDate) {
        this.sessionId = sessionId;
        this.id = id;
        this.type = type;
        this.tuitionFee = tuitionFee;
        this.maxEnrollment = maxEnrollment;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    public Long getSessionId() {
        return sessionId;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public long getTuitionFee() {
        return tuitionFee;
    }
    
    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public String getProgressStatus() {
        return progressStatus;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }
}
