package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.enumerate.EnrollmentType;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class EnrollmentEntity {
    
    private final Long sessionId;
    private final Long id;
    private final EnrollmentType type;
    private final long tuitionFee;
    private final int maxEnrollment;
    private final SessionStatusType sessionStatus;
    
    public EnrollmentEntity(Long sessionId, Long id, EnrollmentType type, long tuitionFee, int maxEnrollment, SessionStatusType sessionStatus) {
        this.sessionId = sessionId;
        this.id = id;
        this.type = type;
        this.tuitionFee = tuitionFee;
        this.maxEnrollment = maxEnrollment;
        this.sessionStatus = sessionStatus;
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
}
