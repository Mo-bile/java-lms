package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.common.Base;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;
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
        return super.getCreatedDate();
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
    
    public void enrollCourse(long userId, long sessionId, Payment payment) throws CanNotJoinException {
        this.enrollCourse(new SessionApply(userId, payment), sessionId);
    }
    
    public void enrollCourse(SessionApply request, long sessionId) throws CanNotJoinException {
        Session session = sessions.findEnrollSession(sessionId);
        session.enrollSession(request);
    }
    
}
