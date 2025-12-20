package nextstep.courses.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Student;
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

    public void updateEnrolledUsers(Session session, Set<Long> originalUsers) {
        List<Student> newStudents = extractEnrolledUserIds(session).stream()
            .filter(id -> !originalUsers.contains(id))
            .map(Student::new)
            .collect(Collectors.toList());

        if (!newStudents.isEmpty()) {
            enrolledUserRepository.saveAll(session.getEnrollment().getId(), new EnrolledUsers(newStudents));
        }
    }

    private List<Long> extractEnrolledUserIds(Session session) {
        return session
            .getEnrollment()
            .getPolicy()
            .getEnrolledUsers()
            .getStudents()
            .stream()
            .map(Student::getId)
            .collect(Collectors.toList());
    }

}