package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.common.Base;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.payments.domain.Payment;

public class Session extends Base {
    
    private final Long id;
    private final String creatorId;
    private final SessionBody body;
    private final Duration duration;
    private final CoverImage coverImage;
    private final Enrollment enrollment;
    
    public Session(String creatorId, SessionBody body, Duration duration, CoverImage coverImage, Enrollment enrollment) {
        this(0L, creatorId, body, duration, coverImage, enrollment, LocalDateTime.now(), null);
    }
    
    public Session(Long id, String creatorId, SessionBody body, Duration duration, CoverImage coverImage, Enrollment enrollment, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.creatorId = creatorId;
        this.body = body;
        this.duration = duration;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }
    
    public void enrollSession(SessionApply request) throws CanNotJoinException {
        enrollment.enroll(request);
    }
    
    public void enrollSession(Long userid) throws CanNotJoinException {
        enrollSession(new SessionApply(userid, null));
    }
    
    public void enrollSession(Long userid, Payment payment) throws CanNotJoinException {
        enrollSession(new SessionApply(userid, payment));
    }
    
    public boolean isSameSessionId(Long id) {
        return Objects.equals(this.id, id);
    }
    
}