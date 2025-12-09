package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final List<Long> enrolledUsers;
    
    public Session(String creatorId, SessionBody body, Duration duration, CoverImage coverImage, Provide provide) {
        this(0L, creatorId, body, duration, coverImage, new SessionStatus(SessionStatusType.PREPARATION), provide, List.of(), LocalDateTime.now(), null);
    }
    
    public Session(Long id, String creatorId, SessionBody body, Duration duration, CoverImage coverImage, SessionStatus status, Provide provide, List<Long> enrolledUsers, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.creatorId = creatorId;
        this.body = body;
        this.duration = duration;
        this.coverImage = coverImage;
        this.status = status;
        this.provide = provide;
        this.enrolledUsers = new ArrayList<>(enrolledUsers);
    }
    
    public void applyFreeSession(Long userId) throws CanNotJoinException {
        status.isApplyStatus();
        provide.applyFree();
        registerUserId(userId);
    }
    
    public void applyPaidSession(Long userId, Long amount) throws CanNotJoinException {
        status.isApplyStatus();
        provide.applyPaid(this.enrolledUsers.size(), amount);
        registerUserId(userId);
    }
    
    public boolean isSameSessionId(Long id) {
        return Objects.equals(this.id, id);
    }
    
    private void registerUserId(Long userId) {
        this.enrolledUsers.add(userId);
    }
}