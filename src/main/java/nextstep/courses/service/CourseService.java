package nextstep.courses.service;

import javax.annotation.Resource;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {
    
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;
    
    @Transactional
    public void apply(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException {
        Course course = courseRepository.findById(courseId);
        
        Payment payment = null;
        if(course.isPaidSession(sessionId)) {
            payment = new PaymentService().payment("id");
        }
        course.apply(loginUser.getId(), sessionId, payment);
        courseRepository.save(course);
    }
    
}
