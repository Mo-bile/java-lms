package nextstep.courses.infrastructure.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseDataAccess {
    
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;
    
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;
    
    @Resource(name = "enrollmentRepository")
    private EnrollmentRepository enrollmentRepository;
    
    @Resource(name = "enrolledUserRepository")
    private EnrolledUserRepository enrolledUserRepository;
    
    public Course findById(Long courseId, Long sessionId) throws CanNotCreateException {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        List<SessionEntity> sessionEntities = sessionRepository.findAllByCourseId(courseId);
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findBySessionId(sessionId);
        List<EnrolledUserEntity> enrolledUserEntities = enrolledUserRepository.findByEnrollmentId(enrollmentEntity.getId());
        return CourseMapper.toModelByJoin(courseEntity, sessionEntities, enrollmentEntity, enrolledUserEntities);
    }
    
    public void updateEnrolledUsers(Course course, long sessionId) throws CanNotJoinException {
        EnrollmentEntity enrollment = enrollmentRepository.findBySessionId(sessionId);
        List<EnrolledUserEntity> savedUsers = enrolledUserRepository.findByEnrollmentId(enrollment.getId());
        
        Set<Long> savedUserIds = savedUsers.stream()
            .map(EnrolledUserEntity::getUserId)
            .collect(Collectors.toSet());
        
        List<Long> currentUserIds = extractEnrolledUserIds(course, sessionId);
        
        List<EnrolledUserEntity> newUsers = currentUserIds.stream()
            .filter(userId -> !savedUserIds.contains(userId))
            .map(userId -> EnrolledUserMapper.toEntity(enrollment.getId(), userId))
            .collect(Collectors.toList());
        
        if(isNotEmpty(newUsers)) {
            enrolledUserRepository.saveAll(newUsers);
        }
    }
    
    private boolean isNotEmpty(List<EnrolledUserEntity> newUsers) {
        return !newUsers.isEmpty();
    }
    
    private List<Long> extractEnrolledUserIds(Course course, long sessionId) throws CanNotJoinException {
        return course.getSessions()
            .findEnrollSession(sessionId)
            .getEnrollment()
            .getPolicy()
            .getEnrolledUsers()
            .getEnrolledUserList();
    }
}

