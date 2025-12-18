package nextstep.courses.domain.enrollment;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentBuilder.aPaidEnrollmentBuilder;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.builder.EnrollmentPolicyBuilder;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class EnrollmentTest {
    
    @Test
    void 무료강의인데_수강료가있으면_에러전파() {
        assertThatThrownBy(() -> {
            aFreeEnrollmentBuilder()
                .withEnrollmentPolicy(
                    EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder().build()
                )
                .build();
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의타입과 정책이 일치하지 않습니다");
    }
    
    @Test
    void 유료강의에_수강신청한다() throws Exception {
        Enrollment enrollment = aPaidEnrollmentBuilder().build();
        assertThatNoException().isThrownBy(() -> {
            enrollment.enroll(10L, new Payment());
        });
    }
    
    @Test
    void 무료강의에_지불_없이_수강신청한다() throws Exception {
        Enrollment enrollment = aFreeEnrollmentBuilder().build();
        assertThatNoException().isThrownBy(() -> enrollment.enroll(10L));
    }
    
}