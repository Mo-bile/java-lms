package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.SessionStatusType;

public class Session extends Base {
    
    private final Long id;
    private final String creatorId;
    private final SessionBody body;
    private final Duration duration;
    private final CoverImage coverImage;
    private final SessionStatus status;
    private final Provide provide;
    private int enrolledCount;
    
    public Session(String creatorId, SessionBody body, Duration duration, CoverImage coverImage, Provide provide) {
        this(0L, creatorId, body, duration, coverImage, new SessionStatus(SessionStatusType.PREPARATION), provide, 0, LocalDateTime.now(), null);
    }
    
    public Session(Long id, String creatorId, SessionBody body, Duration duration, CoverImage coverImage, SessionStatus status, Provide provide, int enrolledCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.creatorId = creatorId;
        this.body = body;
        this.duration = duration;
        this.coverImage = coverImage;
        this.status = status;
        this.provide = provide;
        this.enrolledCount = enrolledCount;
    }
    
    public boolean applyFreeSession() throws CanNotJoinException {
        if(provide.applyFree()) {
            enrolledCount++;
            return true;
        }
        return false;
    }
    
    public boolean applyPaidSession(int amount) throws CanNotJoinException {
        if(provide.applyPaid(this.enrolledCount, amount)) {
            enrolledCount++;
            return true;
        }
        return false;
    }
}