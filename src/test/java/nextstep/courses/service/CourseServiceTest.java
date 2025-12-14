package nextstep.courses.service;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.mockito.BDDMockito.given;

import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.builder.SessionBuilder;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
import nextstep.courses.infrastructure.mapper.EnrollmentMapper;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.courses.infrastructure.repository.enrolledUserRepository.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private EnrolledUserRepository enrolledUserRepository;
    
    @InjectMocks
    private CourseService courseService;
    
    @BeforeEach
    void setUp() throws CanNotCreateException {
        Long courseId = 1L;
        CourseEntity courseEntity = CourseMapper.toEntity(new Course("course title", 1L));
        given(courseRepository.findById(courseId)).willReturn(courseEntity);
        
        Long freeSessionId = 1L;
        Long paidSessionId = 2L;
        Sessions sessions = new Sessions(List.of(SessionBuilder.aFreeSessionBuilder().build(), SessionBuilder.aPaidSessionBuilder().withId(2L).build()));
        List<SessionEntity> sessionEntities = SessionMapper.toEntities(courseId, sessions.getSessions());
        given(sessionRepository.findAllByCourseId(courseId)).willReturn(sessionEntities);
        
        Long freeEnrollmentId = 1L;
        Long paidEnrollmentId = 2L;
        EnrollmentEntity freeEnrollmentEntity = EnrollmentMapper.toEntity(freeSessionId, freeEnrollmentId, EnrollmentBuilder.aFreeEnrollmentBuilder().build());
        EnrollmentEntity paidEnrollmentEntity = EnrollmentMapper.toEntity(paidSessionId, paidEnrollmentId, EnrollmentBuilder.aPaidEnrollmentBuilder().build());
        given(enrollmentRepository.findBySessionId(freeSessionId)).willReturn(freeEnrollmentEntity);
        given(enrollmentRepository.findBySessionId(paidSessionId)).willReturn(paidEnrollmentEntity);
        
        EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(11L, 12L, 13L, 14L, 15L));
        List<EnrolledUserEntity> freeEnrollmentEntities = EnrolledUserMapper.toEntity(freeEnrollmentId, 1L, enrolledUsers);
        List<EnrolledUserEntity> paidEnrollmentEntities = EnrolledUserMapper.toEntity(paidEnrollmentId, 2L, enrolledUsers);
        given(enrolledUserRepository.findByEnrollmentId(freeEnrollmentId)).willReturn(freeEnrollmentEntities);
        given(enrolledUserRepository.findByEnrollmentId(paidEnrollmentId)).willReturn(paidEnrollmentEntities);
    }
    
    @Test
    void enrollSessionTest() throws CanNotCreateException, CanNotJoinException {
        courseService.enroll(JAVAJIGI, 1L, 1L);
        courseService.enroll(SANJIGI, 1L, 2L);
    }
    
}