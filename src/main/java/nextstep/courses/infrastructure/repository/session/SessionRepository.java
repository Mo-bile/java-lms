package nextstep.courses.infrastructure.repository.session;

import java.util.List;
import nextstep.courses.domain.session.Session;

public interface SessionRepository {

    int save(Long courseId, Session session);

    Session findById(Long id);

    List<Session> findAllByCourseId(Long courseId);
}