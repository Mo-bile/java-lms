package nextstep.courses.service;

import java.util.Optional;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.infrastructure.repository.course.CourseRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    private final SessionService sessionService;

    private final CourseRepository courseRepository;

    public CourseService(SessionService sessionService, CourseRepository courseRepository) {
        this.sessionService = sessionService;
        this.courseRepository = courseRepository;
    }
    
    @Transactional
    public void enroll(NsUser loginUser, long courseId, long sessionId) throws CanNotJoinException, CanNotCreateException {
        Optional.ofNullable(courseRepository.findById(courseId)).orElseThrow(() -> new CanNotJoinException("기수가 존재하지 않습니다"));
        Payment payment = new PaymentService().payment("0");
        if (loginUser.isNotSelected()) {
            return;
        }
        sessionService.enroll(loginUser.getId(), sessionId, payment);
    }
}
