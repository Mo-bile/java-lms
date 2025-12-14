package nextstep.courses.infrastructure.repository.session;

import java.util.List;
import nextstep.courses.infrastructure.entity.SessionEntity;

public interface SessionRepository {
    
    int save(SessionEntity session);
    
    SessionEntity findById(Long id);
    
    List<SessionEntity> findAllByCourseId(Long courseId);
}