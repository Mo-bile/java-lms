package nextstep.courses.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {
    
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;
    
    @Resource
    private SessionRepository sessionRepository;
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        List<SessionEntity> sessions = sessionRepository.findAllByCourseId(courseId);
        
        Course course = CourseMapper.toModel(courseEntity);
        
        Payment payment = new PaymentService().payment("id");
        course.enrollCourse(new SessionApply(loginUser.getId(), payment), sessionId);
        courseRepository.save(CourseMapper.toEntity(course));
    }
    
}
