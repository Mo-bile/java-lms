package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.SessionStatusType;
import nextstep.payments.domain.Payment;

public class Session extends Base {
    
    private final Long id;
    private final String creatorId;
    private final SessionBody body;
    private final Duration duration;
    private final CoverImage coverImage;
    private final SessionStatus status;
    private final Provide provide;
    private final EnrolledUsers enrolledUsers;
    
    public Session(String creatorId, SessionBody body, Duration duration, CoverImage coverImage, Provide provide) {
        this(0L, creatorId, body, duration, coverImage, new SessionStatus(SessionStatusType.PREPARATION), provide, new EnrolledUsers(), LocalDateTime.now(), null);
    }
    
    public Session(Long id, String code, SessionBody body, Duration duration, CoverImage coverImage, SessionStatus status, Provide provide, List<Long> enrolledUserIds, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, code, body, duration, coverImage, status, provide, new EnrolledUsers(enrolledUserIds), createdAt, updatedAt);
    }
    
    public Session(Long id, String creatorId, SessionBody body, Duration duration, CoverImage coverImage, SessionStatus status, Provide provide, EnrolledUsers enrolledUsers, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.creatorId = creatorId;
        this.body = body;
        this.duration = duration;
        this.coverImage = coverImage;
        this.status = status;
        this.provide = provide;
        this.enrolledUsers = enrolledUsers;
    }
    
    public void applyFreeSession(Long userId) throws CanNotJoinException {
        status.isApplyStatus();
        provide.applyFree();
        enrolledUsers.registerUserId(userId);
    }
    
    public void applyPaidSession(Long userId, Payment payment) throws CanNotJoinException {
        status.isApplyStatus();
        provide.applyPaid(this.enrolledUsers, payment);
        enrolledUsers.registerUserId(userId);
    }
    
    public boolean isSameSessionId(Long id) {
        return Objects.equals(this.id, id);
    }
    
    
    public boolean isPaidSession() {
        return provide.isPaid();
    }
}