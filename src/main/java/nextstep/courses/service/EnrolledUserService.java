package nextstep.courses.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrolledUserService {

    private final EnrollmentRepository enrollmentRepository;

    private final EnrolledUserRepository enrolledUserRepository;

    public EnrolledUserService(EnrollmentRepository enrollmentRepository, EnrolledUserRepository enrolledUserRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrolledUserRepository = enrolledUserRepository;
    }

    public void updateEnrolledUsers(Session session) {
        Long enrollmentId = enrollmentRepository.findIdBySessionId(session.getId());
        EnrolledUsers savedUsers = enrolledUserRepository.findByEnrollmentId(enrollmentId);

        Set<Long> savedUserIds = new HashSet<>(savedUsers.getEnrolledUserList());
        
        List<Long> currentUserIds = extractEnrolledUserIds(session);
        List<Long> newUserIds = currentUserIds.stream()
            .filter(userId -> !savedUserIds.contains(userId))
            .collect(Collectors.toList());

        if (!newUserIds.isEmpty()) {
            enrolledUserRepository.saveAll(enrollmentId, new EnrolledUsers(newUserIds));
        }
    }

    private List<Long> extractEnrolledUserIds(Session session) {
        return session
            .getEnrollment()
            .getPolicy()
            .getEnrolledUsers()
            .getEnrolledUserList();
    }

}