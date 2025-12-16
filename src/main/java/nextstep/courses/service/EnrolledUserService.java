package nextstep.courses.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrolledUserService {

    @Resource(name = "enrollmentRepository")
    private EnrollmentRepository enrollmentRepository;

    @Resource(name = "enrolledUserRepository")
    private EnrolledUserRepository enrolledUserRepository;

    public void updateEnrolledUsers(Session session, Long enrolledUserId) {
        List<Long> currentUserIds = extractEnrolledUserIds(session);
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findBySessionId(session.getId());

        if (!currentUserIds.contains(enrolledUserId)) {
            enrolledUserRepository.save(EnrolledUserMapper.toEntity(enrollmentEntity.getId(), enrolledUserId));
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