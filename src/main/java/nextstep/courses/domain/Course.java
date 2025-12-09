package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotJoinException;
import nextstep.payments.domain.Payment;

public class Course extends Base {
    
    private final Long id;
    private final String title;
    private final Long creatorId;
    private final List<Session> sessions;
    
    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new ArrayList<>(), LocalDateTime.now(), null);
    }
    
    public Course(String title, Long creatorId, List<Session> sessions) {
        this(0L, title, creatorId, sessions, LocalDateTime.now(), null);
    }
    
    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, title, creatorId, new ArrayList<>(), LocalDateTime.now(), null);
    }
    
    public Course(Long id, String title, Long creatorId, List<Session> sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
    
    public List<Session> getSessions() {
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
        Session session = findToApplySession(sessionId);
        if(payment == null) {
            session.applyFreeSession(userId);
            return;
        }
        session.applyPaidSession(userId, payment);
    }
    
    private Session findToApplySession(long sessionId) throws CanNotJoinException {
        for(Session session: sessions) {
            if(session.isSameSessionId(sessionId)) {
                return session;
            }
        }
        throw new CanNotJoinException("신청하려는 강의가 존재하지 않습니다");
    }
    
    public boolean isPaidSession(Long sessionId) throws CanNotJoinException {
        Session session = findToApplySession(sessionId);
        return session.isPaidSession();
    }
    
}
