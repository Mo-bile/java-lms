package nextstep.courses.service;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aFreeSessionBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import java.util.Set;
import java.util.stream.Collectors;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.repository.coverimages.CoverImagesRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private CoverImagesRepository coverImagesRepository;
    @Mock
    private EnrolledUserService enrolledUserService;

    @InjectMocks
    private SessionService sessionService;

    @Test
    void sessionFindByIdTest() throws CanNotCreateException {
        Long sessionId = 1L;
        EnrolledUsers enrolledUsers = new EnrolledUsers(10L, 11L, 12L, 13L, 14L, 15L);

        Session session = aFreeSessionBuilder()
            .withEnrollment(
                aFreeEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aFreeEnrollmentPolicyBuilder()
                            .withEnrolledUsers(enrolledUsers)
                            .build()
                    )
                    .build()
            )
            .build();

        given(sessionRepository.findById(sessionId)).willReturn(session);
        given(enrollmentRepository.findBySessionId(sessionId)).willReturn(session.getEnrollment());
        given(coverImagesRepository.findBySessionId(sessionId)).willReturn(session.getCoverImages());

        Session found = sessionService.findById(sessionId);

        assertThat(found.getId()).isEqualTo(session.getId());
        assertThat(found.getEnrollment().getPolicy().getEnrolledUsers().getStudents())
            .extracting(Student::getId)
            .containsExactly(10L, 11L, 12L, 13L, 14L, 15L);
        assertThat(found.getBody().getTitle()).isEqualTo(session.getBody().getTitle());
    }

    @Test
    void enrollUpdatesUsers() throws CanNotCreateException, CanNotJoinException {
        Long sessionId = 1L;
        Long userId = 99L;
        Session session = aFreeSessionBuilder().withId(sessionId).build();
        Enrollment enrollment = session.getEnrollment();
        Set<Long> originalUsers = session.getEnrollment().getPolicy().getEnrolledUsers().getStudents().stream().map(Student::getId).collect(Collectors.toSet());

        given(sessionRepository.findById(sessionId)).willReturn(session);
        given(enrollmentRepository.findBySessionId(sessionId)).willReturn(enrollment);
        given(coverImagesRepository.findBySessionId(sessionId)).willReturn(session.getCoverImages());

        Session enrolled = sessionService.enroll(userId, sessionId, new Payment());

        assertThat(enrolled.getEnrollment().getPolicy().getEnrolledUsers().getStudents().stream().map(Student::getId).collect(Collectors.toSet()).contains(userId));
        verify(enrolledUserService).updateEnrolledUsers(enrolled, originalUsers);
    }
}
