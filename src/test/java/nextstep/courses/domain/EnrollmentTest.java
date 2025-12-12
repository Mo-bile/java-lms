package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.builder.FreeEnrollmentBuilder;
import nextstep.courses.domain.builder.PaidEnrollmentBuilder;
import nextstep.courses.domain.builder.PaidEnrollmentPolicyBuilder;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class EnrollmentTest {
    
    @Test
    void 무료강의인데_수강료가있으면_에러전파() {
        assertThatThrownBy(() -> {
            FreeEnrollmentBuilder.aFreeEnrollmentBuilder()
                .withEnrollmentPolicy(
                    PaidEnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder().build()
                )
                .build();
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의타입과 정책이 일치하지 않습니다");
    }
    
    @Test
    void 유료강의에_수강신청한다() throws Exception {
        Enrollment enrollment = PaidEnrollmentBuilder.aPaidEnrollmentBuilder().build();
        assertThatNoException().isThrownBy(() -> {
            enrollment.enroll(10L, new Payment());
        });
    }
    
    @Test
    void 무료강의에_지불_없이_수강신청한다() throws Exception {
        Enrollment enrollment = FreeEnrollmentBuilder.aFreeEnrollmentBuilder().build();
        assertThatNoException().isThrownBy(() -> enrollment.enroll(10L));
    }
    
}