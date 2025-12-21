package nextstep.courses.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.session.CoverImages;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.coverimages.CoverImagesRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final CoverImagesRepository coverImagesRepository;

    private final EnrolledUserService enrolledUserService;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository, CoverImagesRepository coverImagesRepository, EnrolledUserService enrolledUserService) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.enrolledUserService = enrolledUserService;
        this.coverImagesRepository = coverImagesRepository;
    }

    public Session findById(Long id) throws CanNotCreateException {
        Session session = sessionRepository.findById(id);
        Enrollment enrollmentAndUserList = enrollmentRepository.findBySessionId(id);
        CoverImages coverImages = coverImagesRepository.findBySessionId(id);

        return SessionMapper.attachEnrollment(session, enrollmentAndUserList, coverImages);
    }

    public Session enroll(Long userId, Long sessionId, Payment payment) throws CanNotCreateException, CanNotJoinException {
        Session session = findById(sessionId);
        Set<Long> originalUsers = originalUserCache(session);
        session.enrollSession(new SessionApply(userId, payment));
        enrolledUserService.updateEnrolledUsers(session, originalUsers);
        return session;
    }

    private Set<Long> originalUserCache(Session session) {
        return new HashSet<>(session.getEnrollment()
            .getPolicy()
            .getEnrolledUsers()
            .getStudents()
            .stream()
            .map(Student::getId)
            .collect(Collectors.toSet())
        );
    }
}