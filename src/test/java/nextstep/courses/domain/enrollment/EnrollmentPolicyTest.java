package nextstep.courses.domain.enrollment;

import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.SessionApply;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class EnrollmentPolicyTest {
    
    @Test
    void 수강신청_인원이_초과하면_에러전파() {
        EnrollmentPolicy enrollmentPolicy = aPaidEnrollmentPolicyBuilder()
            .withEnrolledUsers(new EnrolledUsers(10))
            .build();
        SessionApply sessionApply = new SessionApply(10L, new Payment());
        
        assertThatThrownBy(() -> {
            enrollmentPolicy.enroll(sessionApply);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 정원을 초과했다");
    }

    @Test
    void 무료강의인데_수강신청_시_정원체크를_해도_상관없다() {
        SessionApply sessionApply = new SessionApply(1000L, new Payment());
        EnrollmentPolicy enrollmentPolicy = aFreeEnrollmentPolicyBuilder().build();
        
        assertThatNoException().isThrownBy(() -> {
            enrollmentPolicy.enroll(sessionApply);
        });
    }
}