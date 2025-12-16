package nextstep.courses.service;

import javax.annotation.Resource;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    private final SessionService sessionService;

    private final EnrolledUserService enrolledUserService;

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public CourseService(SessionService sessionService, EnrolledUserService enrolledUserService, CourseRepository courseRepository) {
        this.sessionService = sessionService;
        this.enrolledUserService = enrolledUserService;
        this.courseRepository = courseRepository;
    }
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException, CanNotCreateException {
        Course course = CourseMapper.toModel(courseRepository.findById(courseId));
        Session session = sessionService.findById(sessionId);
        Payment payment = new PaymentService().payment("0");

        course.enrollCourse(new SessionApply(loginUser.getId(), payment), sessionId);
        enrolledUserService.updateEnrolledUsers(session, loginUser.getId());
    }
}
