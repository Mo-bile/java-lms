package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.enumerate.CoverImageType;
import nextstep.courses.enumerate.EnrollmentType;
import nextstep.courses.enumerate.SessionStatusType;
import nextstep.payments.domain.Payment;
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
                new Enrollment(
                    EnrollmentType.FREE,
                    new EnrollmentPolicy(
                        FreeEnrollmentCondition.INSTANCE,
                        new EnrolledUsers(List.of(11L, 12L, 13L, 14L, 15L)),
                        new SessionStatus(SessionStatusType.RECRUITING))),
                LocalDateTime.now(),
                null
            );
            
            paidSession = new Session(
                2L,
                "1",
                new SessionBody("title", "content"),
                new Duration(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)),
                new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200),
                new Enrollment(
                    EnrollmentType.PAID,
                    new EnrollmentPolicy(
                        new PaidEnrollmentCondition(5L, 10),
                        new EnrolledUsers(List.of(11L, 12L, 13L, 14L, 15L)),
                        new SessionStatus(SessionStatusType.RECRUITING))),
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
                new Enrollment(EnrollmentType.FREE)
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
                new Enrollment(EnrollmentType.PAID, new EnrollmentPolicy(new PaidEnrollmentCondition(10L, 10)))
            );
        });
    }
    
    @Test
    void 무료_session을_수강신청한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            freeSession.enrollSession(NsUserTest.JAVAJIGI.getId());
        });
    }
    
    @Test
    void 유료_session을_수강신청한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            paidSession.enrollSession(NsUserTest.JAVAJIGI.getId(), new Payment());
        });
    }
    
}