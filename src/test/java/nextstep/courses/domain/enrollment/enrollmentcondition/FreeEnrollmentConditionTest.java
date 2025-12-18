package nextstep.courses.domain.enrollment.enrollmentcondition;

import static org.assertj.core.api.Assertions.assertThatNoException;

import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class FreeEnrollmentConditionTest {
    
    @Test
    void 무료강의에_지불_없이_수강신청한다() throws Exception {
        FreeEnrollmentCondition freeEnrollmentCondition = FreeEnrollmentCondition.INSTANCE;
        assertThatNoException().isThrownBy(() -> freeEnrollmentCondition.isPaid(new Payment()));
        assertThatNoException().isThrownBy(() -> freeEnrollmentCondition.isFull(new EnrolledUsers(100)));
    }
}