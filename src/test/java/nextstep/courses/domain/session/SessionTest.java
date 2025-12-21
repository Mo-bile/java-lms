package nextstep.courses.domain.session;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentBuilder.aPaidEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aFreeSessionBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aPaidSessionBuilder;
import static org.assertj.core.api.Assertions.assertThatNoException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {
    
    public static final Session freeSession;
    public static final Session paidSession;
    
    static {
        try {
            freeSession = aFreeSessionBuilder().build();
            paidSession = aPaidSessionBuilder().build();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void 무료_session을_생성한다() {
        assertThatNoException().isThrownBy(() -> {
            aFreeSessionBuilder().build();
        });
    }
    
    
    @Test
    void 유료_session을_생성한다() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            aPaidSessionBuilder().build();
        });
    }
    
    @Test
    void 무료_session을_수강신청한다() {
        assertThatNoException().isThrownBy(() -> {
            Session freeSession = aFreeSessionBuilder()
                .withEnrollment(
                    aFreeEnrollmentBuilder()
                        .withEnrollmentPolicy(
                            aFreeEnrollmentPolicyBuilder()
                                .withEnrolledUsers(new EnrolledUsers(10L, 11L, 12L, 13L, 14L, 15L))
                                .build()
                        ).build()
                )
                .build();
            freeSession.enrollSession(NsUserTest.JAVAJIGI.getId());
        });
    }
    
    @Test
    void 유료_session을_수강신청한다() {
        assertThatNoException().isThrownBy(() -> {
            Session paidSession = aPaidSessionBuilder()
                .withEnrollment(
                    aPaidEnrollmentBuilder()
                        .withEnrollmentPolicy(
                            aPaidEnrollmentPolicyBuilder()
                                .withEnrolledUsers(new EnrolledUsers(10L, 11L, 12L, 13L, 14L, 15L))
                                .build()
                        ).build()
                )
                .build();
            paidSession.enrollSession(NsUserTest.JAVAJIGI.getId(), new Payment());
        });
    }
    
}