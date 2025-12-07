package nextstep.courses.domain;

import nextstep.courses.enumerate.SessionStatusType;

public class SessionStatus {
    
    private final SessionStatusType sessionStatusType;
    
    public SessionStatus(SessionStatusType sessionStatusType) {
        this.sessionStatusType = sessionStatusType;
    }
    
    public boolean isApplyStatus() {
        return this.sessionStatusType == SessionStatusType.RECRUITING;
    }
    
}
