package nextstep.courses.infrastructure.repository.enrollment;

import nextstep.courses.domain.enrollment.Enrollment;

public interface EnrollmentRepository {

    int save(Long sessionId, Enrollment enrollment);

    Enrollment findById(Long id);

    Enrollment findBySessionId(Long sessionId);

    Long findIdBySessionId(Long sessionId);
}
