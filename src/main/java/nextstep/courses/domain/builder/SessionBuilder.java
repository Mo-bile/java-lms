package nextstep.courses.domain.builder;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentBuilder.aPaidEnrollmentBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enumerate.CoverImageType;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Duration;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionBody;

public class SessionBuilder {
    
    private Long id = 1L;
    private String creatorId = "1";
    private SessionBody body = new SessionBody("title", "content");
    private Duration duration = new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
    private CoverImage coverImage = new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200);
    private Enrollment enrollment;
    
    public static SessionBuilder aPaidSessionBuilder() throws CanNotCreateException {
        return new SessionBuilder()
            .withEnrollment(aPaidEnrollmentBuilder().build());
    }
    
    public static SessionBuilder aFreeSessionBuilder() throws CanNotCreateException {
        return new SessionBuilder()
            .withEnrollment(aFreeEnrollmentBuilder().build());
    }
    
    public SessionBuilder(SessionBuilder copy) throws CanNotCreateException {
        this.id = copy.id;
        this.creatorId = copy.creatorId;
        this.body = copy.body;
        this.duration = copy.duration;
        this.coverImage = copy.coverImage;
        this.enrollment = copy.enrollment;
    }
    
    private SessionBuilder() throws CanNotCreateException {}
    
    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public SessionBuilder withCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    
    public SessionBuilder withBody(SessionBody body) {
        this.body = body;
        return this;
    }
    
    public SessionBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
    
    public SessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }
    
    public SessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }
    
    public Session build() {
        return new Session(id, creatorId, body, duration, coverImage, enrollment, LocalDateTime.now(), null);
    }
    
    public SessionBuilder but() throws CanNotCreateException {
        return new SessionBuilder(this);
    }
}
