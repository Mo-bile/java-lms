package nextstep.courses.infrastructure.repository.enrolleduser;

import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Student;

public interface EnrolledUserRepository {

    int saveAll(Long enrollmentId, EnrolledUsers enrolledUsers);

    int save(Long enrollmentId, Long userId, Student student);

    EnrolledUsers findById(Long id);

    EnrolledUsers findByEnrollmentId(Long enrollmentId);
}
