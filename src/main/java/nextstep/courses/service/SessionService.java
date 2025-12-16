package nextstep.courses.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "enrollmentRepository")
    private EnrollmentRepository enrollmentRepository;

    @Resource(name = "enrolledUserRepository")
    private EnrolledUserRepository enrolledUserRepository;

    public Session findById(Long id) throws CanNotCreateException {
        SessionEntity sessionEntities = sessionRepository.findById(id);
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findBySessionId(id);
        List<EnrolledUserEntity> enrolledUserEntities = enrolledUserRepository.findByEnrollmentId(enrollmentEntity.getId());
        return SessionMapper.toModelByJoin(sessionEntities, enrollmentEntity, enrolledUserEntities);
    }
}