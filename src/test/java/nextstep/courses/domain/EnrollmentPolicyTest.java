package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.enumerate.SessionStatusType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class EnrollmentPolicyTest {
    
    @Test
    void 수강신청_인원이_초과하면_에러전파() throws Exception {
        EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
            new PaidEnrollmentCondition(5L, 10),
            new EnrolledUsers(10),
            new SessionStatus(SessionStatusType.RECRUITING)
        );
        SessionApply sessionApply = new SessionApply(10L, new Payment());
        
        assertThatThrownBy(() -> {
            enrollmentPolicy.enroll(sessionApply);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 정원을 초과했다");
    }

    @Test
    void 무료강의인데_수강신청_시_정원체크를_해도_상관없다() throws Exception {
        SessionApply sessionApply = new SessionApply(1000L, new Payment());
        EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
            FreeEnrollmentCondition.INSTANCE,
            new EnrolledUsers(8),
            new SessionStatus(SessionStatusType.RECRUITING)
        );
        
        assertThatNoException().isThrownBy(() -> {
            enrollmentPolicy.enroll(sessionApply);
        });
    }
}