package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.courses.enumerate.CoverImageType;
import nextstep.courses.enumerate.ProvideType;
import org.junit.jupiter.api.Test;

class SessionTest {
    
    public static final Session freeSession;
    public static final Session paidSession;
    
    static {
        try {
            freeSession = new Session(
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new Provide(ProvideType.FREE)
            );
            paidSession = new Session(
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new Provide(ProvideType.PAID, new ProvidePolicy(10, 10))
            );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Test
    void 무료_session을_생성한다() throws Exception {
        new Session(
            "1",
            new SessionBody("title", "content"),
            new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
            new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
            new Provide(ProvideType.FREE)
        );
    }
    
    @Test
    void 유료_session을_생성한다() throws Exception {
        new Session(
            "1",
            new SessionBody("title", "content"),
            new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
            new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
            new Provide(ProvideType.PAID, new ProvidePolicy(10, 10))
        );
    }
    
    @Test
    void 무료_session을_수강신청한다() throws Exception {
        assertThat(freeSession.applyFreeSession())
            .isTrue();
    }
    
    @Test
    void 유료_session을_수강신청한다() throws Exception {
        assertThat(paidSession.applyPaidSession(10))
            .isTrue();
        
    }
    
}