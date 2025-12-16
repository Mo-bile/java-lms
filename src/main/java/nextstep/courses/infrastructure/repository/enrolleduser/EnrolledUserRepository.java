package nextstep.courses.infrastructure.repository.enrolleduser;

import nextstep.courses.domain.enrollment.EnrolledUsers;

public interface EnrolledUserRepository {

    int saveAll(Long enrollmentId, EnrolledUsers enrolledUsers);

    int save(Long enrollmentId, Long userId);

    EnrolledUsers findById(Long id);

    EnrolledUsers findByEnrollmentId(Long enrollmentId);
}
