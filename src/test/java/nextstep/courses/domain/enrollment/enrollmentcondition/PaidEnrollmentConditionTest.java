package nextstep.courses.domain.enrollment.enrollmentcondition;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaidEnrollmentConditionTest {
    
    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void 수강료가_음수이면_에러전파(long tuitionFee) {
        assertThatThrownBy(() -> new PaidEnrollmentCondition(tuitionFee, 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강료는 양수이어야 한다");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 정원이_음수이면_에러전파(int maxEnrollment) {
        assertThatThrownBy(() -> new PaidEnrollmentCondition(5L, maxEnrollment))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("정원은 양수이어야 한다");
    }
    
    @Test
    void 유료강의에_수강신청한다() {
        PaidEnrollmentCondition paidEnrollmentCondition = new PaidEnrollmentCondition(5L, 10);
        assertThatNoException().isThrownBy(() -> {
            paidEnrollmentCondition.isPaid(new Payment());
            assertThatNoException().isThrownBy(() -> paidEnrollmentCondition.isFull(new EnrolledUsers(8)));
        });
    }
    
    @Test
    void 유료강의에_수강신청에_정원이_초과하면_에러전파() {
        PaidEnrollmentCondition paidEnrollmentCondition
            = new PaidEnrollmentCondition(5L, 10);
        
        assertThatThrownBy(() -> paidEnrollmentCondition.isFull(new EnrolledUsers(10)))
            .isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 정원을 초과했다");
    }
}