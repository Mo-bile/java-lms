package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.enumerate.CoverImageType;
import nextstep.courses.enumerate.ProvideType;
import nextstep.courses.enumerate.SessionStatusType;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {
    
    public static final Session freeSession;
    public static final Session paidSession;
    
    static {
        try {
            freeSession = new Session(
                1L,
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new SessionStatus(SessionStatusType.RECRUITING),
                new Provide(ProvideType.FREE),
                List.of(11L, 12L, 13L, 14L, 15L),
                LocalDateTime.now(),
                null
            );
            
            paidSession = new Session(
                2L,
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new SessionStatus(SessionStatusType.RECRUITING),
                new Provide(ProvideType.PAID, new ProvidePolicy(10, 10L)),
                List.of(11L, 12L, 13L, 14L, 15L),
                LocalDateTime.now(),
                null
            );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Test
    void 무료_session을_생성한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            new Session(
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new Provide(ProvideType.FREE)
            );
        });
    }
    
    @Test
    void 유료_session을_생성한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            new Session(
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new Provide(ProvideType.PAID, new ProvidePolicy(10, 10L))
            );
        });
    }
    
    @Test
    void 무료_session을_수강신청한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            freeSession.applyFreeSession(NsUserTest.JAVAJIGI.getId());
        });
    }
    
    @Test
    void 유료_session을_수강신청한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            paidSession.applyPaidSession(NsUserTest.JAVAJIGI.getId(), 10L);
        });
    }
    
}