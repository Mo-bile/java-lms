package nextstep.courses.infrastructure.repository.enrolledUserRepository;

import java.util.List;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;

public interface EnrolledUserRepository {
    
    int saveAll(List<EnrolledUserEntity> enrolledUserEntities);
    
    int save(EnrolledUserEntity enrolledUserEntity);
    
    EnrolledUserEntity findById(Long id);
    
    List<EnrolledUserEntity> findByEnrollmentId(Long enrollmentId);
}
