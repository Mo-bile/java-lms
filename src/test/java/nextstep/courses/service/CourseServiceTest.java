package nextstep.courses.service;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentBuilder.aPaidEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aFreeSessionBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aPaidSessionBuilder;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import java.util.List;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    
    @Mock
    private SessionService sessionService;

    @Mock
    private EnrolledUserService enrolledUserService;

    @Mock
    private CourseRepository courseRepository;
    
    @InjectMocks
    private CourseService courseService;
    
    @Test
    void enrollSessionTest() throws Exception {
        long freeSessionId = 1L;
        long paidSessionId = 2L;
        Session freeSession = aFreeSessionBuilder()
            .withId(freeSessionId)
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
            .withId(paidSessionId)
            .withEnrollment(
                aPaidEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aPaidEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(List.of(10L, 11L, 12L, 13L, 14L, 15L)))
                            .build()
                    ).build()
            )
            .build();
        long courseId = 1L;
        Course course = new Course("course title", courseId, List.of(freeSession, paidSession));

        given(courseRepository.findById(courseId)).willReturn(course);
        given(sessionService.findById(freeSessionId)).willReturn(freeSession);
        given(sessionService.findById(paidSessionId)).willReturn(paidSession);
        
        courseService.enroll(JAVAJIGI, courseId, freeSessionId);
        courseService.enroll(SANJIGI, courseId, paidSessionId);

        verify(enrolledUserService, Mockito.times(2)).updateEnrolledUsers(any(Session.class));
    }
    
}
