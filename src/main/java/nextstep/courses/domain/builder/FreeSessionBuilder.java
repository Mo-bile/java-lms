package nextstep.courses.domain.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.*;
import nextstep.courses.domain.enumerate.CoverImageType;

public class FreeSessionBuilder {
    
    private Long id = 1L;
    private String creatorId = "1";
    private SessionBody body = new SessionBody("title", "content");
    private Duration duration = new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
    private CoverImage coverImage = new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200);
    private Enrollment enrollment = FreeEnrollmentBuilder.aFreeEnrollmentBuilder().build();
    
    public static FreeSessionBuilder aFreeSessionBuilder() throws CanNotCreateException {
        return new FreeSessionBuilder();
    }
    
    public FreeSessionBuilder(FreeSessionBuilder copy) throws CanNotCreateException {
        this.id = copy.id;
        this.creatorId = copy.creatorId;
        this.body = copy.body;
        this.duration = copy.duration;
        this.coverImage = copy.coverImage;
        this.enrollment = copy.enrollment;
    }
    
    private FreeSessionBuilder() throws CanNotCreateException {}
    
    public FreeSessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public FreeSessionBuilder withCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    
    public FreeSessionBuilder withBody(SessionBody body) {
        this.body = body;
        return this;
    }
    
    public FreeSessionBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
    
    public FreeSessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }
    
    public FreeSessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }
    
    public Session build() {
        return new Session(id, creatorId, body, duration, coverImage, enrollment, LocalDateTime.now(), null);
    }
    
    public FreeSessionBuilder but() throws CanNotCreateException {
        return new FreeSessionBuilder(this);
    }
}
