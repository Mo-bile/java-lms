package nextstep.courses.domain.enrollment;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class SessionStatus {
    
    private final SessionStatusType sessionStatusType;
    
    public SessionStatus() {
        this(SessionStatusType.PREPARATION);
    }
    
    public SessionStatus(SessionStatusType sessionStatusType) {
        this.sessionStatusType = sessionStatusType;
    }
    
    public void isApplyStatus() throws CanNotJoinException {
        if(this.sessionStatusType != SessionStatusType.RECRUITING) {
            throw new CanNotJoinException("모집 중 일때만 신청 가능합니다");
        }
    }
    
}
