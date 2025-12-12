package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.enumerate.EnrollmentType;
import nextstep.courses.enumerate.SessionStatusType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class EnrollmentTest {
    
    @Test
    void 무료강의인데_수강료가있으면_에러전파() {
        assertThatThrownBy(() -> {
            new Enrollment(EnrollmentType.FREE, new EnrollmentPolicy(new PaidEnrollmentCondition(5L, 10)));
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의타입과 정책이 일치하지 않습니다");
    }
    
    @Test
    void 유료강의에_수강신청한다() throws Exception {
        Enrollment enrollment =
            new Enrollment(
                EnrollmentType.PAID,
                new EnrollmentPolicy(
                    new PaidEnrollmentCondition(5L, 10),
                    new EnrolledUsers(8),
                    new SessionStatus(SessionStatusType.RECRUITING)
                )
            );
        
        assertThatNoException().isThrownBy(() -> {
            enrollment.enroll(10L, new Payment());
        });
    }
    
    @Test
    void 무료강의에_지불_없이_수강신청한다() throws Exception {
        Enrollment enrollment =
            new Enrollment(
                EnrollmentType.FREE,
                new EnrollmentPolicy(
                    FreeEnrollmentCondition.INSTANCE,
                    new EnrolledUsers(8),
                    new SessionStatus(SessionStatusType.RECRUITING)
                )
            );
        assertThatNoException().isThrownBy(() -> enrollment.enroll(10L));
    }
    
}