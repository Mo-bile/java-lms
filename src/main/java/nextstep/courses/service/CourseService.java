package nextstep.courses.service;

import javax.annotation.Resource;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.SessionApply;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {
    
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException {
        Course course = courseRepository.findById(courseId);
        
        Payment payment = new PaymentService().payment("id");
        course.enrollCourse(new SessionApply(loginUser.getId(), payment), sessionId);
        courseRepository.save(course);
    }
    
}
