package nextstep.courses.service;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.infrastructure.repository.CourseDataAccess;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    
    CourseDataAccess courseDataAccess;
    
    public CourseService(CourseDataAccess courseDataAccess) {
        this.courseDataAccess = courseDataAccess;
    }
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException, CanNotCreateException {
        Course course = courseDataAccess.findById(courseId, sessionId);
        Payment payment = new PaymentService().payment("0");
        course.enrollCourse(new SessionApply(loginUser.getId(), payment), sessionId);
        courseDataAccess.updateEnrolledUsers(course, sessionId);
    }
}
