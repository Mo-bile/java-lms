package nextstep.courses.service;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final EnrolledUserService enrolledUserService;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository, EnrolledUserService enrolledUserService) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.enrolledUserService = enrolledUserService;
    }

    public Session findById(Long id) throws CanNotCreateException {
        Session session = sessionRepository.findById(id);
        Enrollment enrollmentAndUserList = enrollmentRepository.findBySessionId(id);
        return SessionMapper.attachEnrollment(session, enrollmentAndUserList);
    }

    public Session enroll(Long userId, Long sessionId, Payment payment) throws CanNotCreateException, CanNotJoinException {
        Session session = findById(sessionId);
        session.enrollSession(new SessionApply(userId, payment));
        enrolledUserService.updateEnrolledUsers(session);
        return session;
    }
}