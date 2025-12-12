package nextstep.courses.domain.course;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentBuilder.aPaidEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aFreeSessionBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aPaidSessionBuilder;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class CourseTest {
    
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
    void 수강신청하는_session이_없으면_예외전파() {
        Course course = new Course("title", 1L, List.of(freeSession, paidSession));
        assertThatThrownBy(() -> {
            course.enrollCourse(NsUserTest.JAVAJIGI.getId(), 3L, null);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("신청하려는 강의가 존재하지 않습니다");
    }
    
    @Test
    void 무료강의_수강신청() throws CanNotCreateException {
        Session freeSession = aFreeSessionBuilder()
            .withEnrollment(
                aFreeEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aFreeEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(List.of(10L, 11L, 12L, 13L, 14L, 15L)))
                            .build()
                    ).build()
            )
            .build();
        Session paidSession = aPaidSessionBuilder()
            .withId(2L)
            .withEnrollment(
                aPaidEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aPaidEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(List.of(10L, 11L, 12L, 13L, 14L, 15L)))
                            .build()
                    ).build()
            )
            .build();
        
        Course course = new Course("title", 1L, List.of(freeSession, paidSession));
        
        assertThatNoException().isThrownBy(() -> {
            course.enrollCourse(NsUserTest.JAVAJIGI.getId(), 1L, null);
        });
    }
    
    @Test
    void 유료강의_수강신청() throws CanNotCreateException {
        Session freeSession = aFreeSessionBuilder()
            .withEnrollment(
                aFreeEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aFreeEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(List.of(10L, 11L, 12L, 13L, 14L, 15L)))
                            .build()
                    ).build()
            )
            .build();
        Session paidSession = aPaidSessionBuilder()
            .withId(2L)
            .withEnrollment(
                aPaidEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aPaidEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(List.of(10L, 11L, 12L, 13L, 14L, 15L)))
                            .build()
                    ).build()
            )
            .build();
        Course course = new Course("title", 1L, List.of(freeSession, paidSession));
        
        assertThatNoException().isThrownBy(() -> {
            course.enrollCourse(NsUserTest.SANJIGI.getId(), 2L, new Payment());
        });
    }
    
}