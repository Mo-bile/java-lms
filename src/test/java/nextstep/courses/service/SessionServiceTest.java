package nextstep.courses.service;

import static nextstep.courses.domain.builder.EnrollmentBuilder.aFreeEnrollmentBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.SessionBuilder.aFreeSessionBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
import nextstep.courses.infrastructure.mapper.EnrollmentMapper;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private EnrolledUserService enrolledUserService;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private EnrolledUserRepository enrolledUserRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    void sessionFindByIdTest() throws CanNotCreateException {
        Long sessionId = 1L;
        Long enrollmentId = 10L;
        Long courseId = 5L;
        List<Long> enrolledUserIds = List.of(10L, 11L, 12L, 13L, 14L, 15L);

        Session session = aFreeSessionBuilder()
            .withId(sessionId)
            .withEnrollment(
                aFreeEnrollmentBuilder()
                    .withEnrollmentPolicy(
                        aFreeEnrollmentPolicyBuilder()
                            .withEnrolledUsers(new EnrolledUsers(enrolledUserIds))
                            .build()
                    )
                    .build()
            )
            .build();

        SessionEntity sessionEntity = SessionMapper.toEntity(courseId, session);
        EnrollmentEntity enrollmentEntity = EnrollmentMapper.toEntity(sessionId, enrollmentId, session.getEnrollment());
        List<EnrolledUserEntity> enrolledUserEntities = EnrolledUserMapper.toEntityList(enrollmentId, enrollmentId, session.getEnrollment().getPolicy().getEnrolledUsers());

        given(sessionRepository.findById(sessionId)).willReturn(sessionEntity);
        given(enrollmentRepository.findBySessionId(sessionId)).willReturn(enrollmentEntity);
        given(enrolledUserRepository.findByEnrollmentId(enrollmentId)).willReturn(enrolledUserEntities);

        Session found = sessionService.findById(sessionId);

        assertThat(found.getId()).isEqualTo(session.getId());
        assertThat(found.getEnrollment().getPolicy().getEnrolledUsers().getEnrolledUserList())
            .containsExactlyElementsOf(enrolledUserIds);
        assertThat(found.getBody().getTitle()).isEqualTo(session.getBody().getTitle());
    }
}
