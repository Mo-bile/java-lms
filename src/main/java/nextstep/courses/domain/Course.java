package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.CanNotJoinException;
import nextstep.payments.domain.Payment;

public class Course extends Base {
    
    private final Long id;
    private final String title;
    private final Long creatorId;
    private final Sessions sessions;
    
    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new Sessions(), LocalDateTime.now(), null);
    }
    
    public Course(String title, Long creatorId, List<Session> sessions) {
        this(0L, title, creatorId, new Sessions(sessions), LocalDateTime.now(), null);
    }
    
    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, title, creatorId, new Sessions(), LocalDateTime.now(), null);
    }
    
    public Course(Long id, String title, Long creatorId, Sessions sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return super.createdDate;
    }
    
    public Sessions getSessions() {
        return sessions;
    }
    
    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", creatorId=" + creatorId +
            ", sessions=" + sessions +
            '}';
    }
    
    public void apply(long userId, long sessionId, Payment payment) throws CanNotJoinException {
        Session session = sessions.findToApplySession(sessionId);
        if(payment == null) {
            session.applyFreeSession(userId);
            return;
        }
        session.applyPaidSession(userId, payment);
    }
    
    public boolean isPaidSession(Long sessionId) throws CanNotJoinException {
        Session session = sessions.findToApplySession(sessionId);
        return session.isPaidSession();
    }
    
}
