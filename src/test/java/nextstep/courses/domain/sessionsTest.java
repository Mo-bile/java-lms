package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.CoverImageType;
import nextstep.courses.enumerate.ProvideType;
import nextstep.courses.enumerate.SessionStatusType;
import org.junit.jupiter.api.Test;

class sessionsTest {
    
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
                List.of(1L, 2L, 3L, 4L, 5L),
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
                List.of(1L, 2L, 3L, 4L, 5L),
                LocalDateTime.now(),
                null
            );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void 찾고자하는_세션이_없으면_에러전파() throws CanNotJoinException {
        assertThatThrownBy(() -> {
            new Sessions(freeSession, paidSession)
                .findToApplySession(3L);
        })
            .isInstanceOf(CanNotJoinException.class)
            .hasMessage("신청하려는 강의가 존재하지 않습니다");
    }
}