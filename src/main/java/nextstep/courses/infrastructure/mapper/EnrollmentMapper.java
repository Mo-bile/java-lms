package nextstep.courses.infrastructure.mapper;

import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollment.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.domain.enumerate.EnrollmentType;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;

public class EnrollmentMapper {
    
    public static Enrollment toModelByJoin(EnrollmentEntity entity, List<EnrolledUserEntity> enrolledUserList) throws CanNotCreateException {
        EnrollmentType type = entity.getType();
        EnrolledUsers enrolledUsers = EnrolledUserMapper.toModel(enrolledUserList);
        SessionStatus sessionStatus = new SessionStatus(entity.getSessionStatus());
        EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
            type == EnrollmentType.FREE
                ? FreeEnrollmentCondition.INSTANCE
                : new PaidEnrollmentCondition(entity.getTuitionFee(), entity.getMaxEnrollment()),
            enrolledUsers,
            sessionStatus);
        return new Enrollment(type, enrollmentPolicy);
    }
    
    public static Enrollment toModel(EnrollmentEntity entity) throws CanNotCreateException {
        EnrollmentType type = entity.getType();
        SessionStatus sessionStatus = new SessionStatus(entity.getSessionStatus());
        EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
            type == EnrollmentType.FREE
                ? FreeEnrollmentCondition.INSTANCE
                : new PaidEnrollmentCondition(entity.getTuitionFee(), entity.getMaxEnrollment()),
            sessionStatus);
        return new Enrollment(type, enrollmentPolicy);
    }
    
    public static EnrollmentEntity toEntity(Long sessionId, Enrollment enrollment) {
        EnrollmentPolicy policy = enrollment.getPolicy();
        return new EnrollmentEntity(
            sessionId,
            null,
            enrollment.getType(),
            policy.getEnrollmentCondition().tuitionFee().orElse(0L),
            policy.getEnrollmentCondition().maxEnrollment().orElse(0),
            policy.getStatus().getSessionStatusType()
        );
    }
}
