package nextstep.courses.service;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Session findById(Long id) throws CanNotCreateException {
        Session session = sessionRepository.findById(id);
        Enrollment enrollment = enrollmentRepository.findBySessionId(id);
        return SessionMapper.attachEnrollment(session, enrollment);
    }
}