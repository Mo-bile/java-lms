package nextstep.courses.infrastructure.repository.enrollment;

import nextstep.courses.infrastructure.entity.EnrollmentEntity;

public interface EnrollmentRepository {
    
    int save(EnrollmentEntity enrollmentEntity);
    
    EnrollmentEntity findById(Long id);
    
    EnrollmentEntity findBySessionId(Long sessionId);
}
