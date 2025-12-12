package nextstep.courses.domain.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.*;
import nextstep.courses.domain.enumerate.CoverImageType;

public class PaidSessionBuilder {
    
    private Long id = 2L;
    private String creatorId = "1";
    private SessionBody body = new SessionBody("title", "content");
    private Duration duration = new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
    private CoverImage coverImage = new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200);
    private Enrollment enrollment = PaidEnrollmentBuilder.aPaidEnrollmentBuilder().build();
    
    public static PaidSessionBuilder aPaidSessionBuilder() throws CanNotCreateException {
        return new PaidSessionBuilder();
    }
    
    public PaidSessionBuilder(PaidSessionBuilder copy) throws CanNotCreateException {
        this.id = copy.id;
        this.creatorId = copy.creatorId;
        this.body = copy.body;
        this.duration = copy.duration;
        this.coverImage = copy.coverImage;
        this.enrollment = copy.enrollment;
    }
    
    private PaidSessionBuilder() throws CanNotCreateException {}
    
    public PaidSessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public PaidSessionBuilder withCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    
    public PaidSessionBuilder withBody(SessionBody body) {
        this.body = body;
        return this;
    }
    
    public PaidSessionBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
    
    public PaidSessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }
    
    public PaidSessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }
    
    public Session build() {
        return new Session(id, creatorId, body, duration, coverImage, enrollment, LocalDateTime.now(), null);
    }
    
    public PaidSessionBuilder but() throws CanNotCreateException {
        return new PaidSessionBuilder(this);
    }
}
