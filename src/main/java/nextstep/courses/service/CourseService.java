package nextstep.courses.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.courses.infrastructure.repository.enrolledUserRepository.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {
    
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;
    
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;
    
    @Resource(name = "enrollmentRepository")
    private EnrollmentRepository enrollmentRepository;
    
    @Resource(name = "enrolledUserRepository")
    private EnrolledUserRepository enrolledUserRepository;
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException, CanNotCreateException {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        List<SessionEntity> sessionEntities = sessionRepository.findAllByCourseId(courseId);
        EnrollmentEntity enrollment = enrollmentRepository.findBySessionId(sessionId);
        List<EnrolledUserEntity> enrolledUserEntities = enrolledUserRepository.findByEnrollmentId(enrollment.getId());
        
        Course course = CourseMapper.toModelByJoin(courseEntity, sessionEntities, enrollment, enrolledUserEntities);
        
        Payment payment = new PaymentService().payment("id");
        course.enrollCourse(new SessionApply(loginUser.getId(), payment), sessionId);
        courseRepository.save(CourseMapper.toEntity(course));
    }
    
}
