package nextstep.courses.infrastructure.mapper;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.domain.enrollment.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollment.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.domain.enumerate.EnrollmentType;
import nextstep.courses.domain.enumerate.ProgressStatus;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;

public final class EnrollmentMapper {

    public static Enrollment toModelWithEnrolledUsers(EnrollmentEntity entity, EnrolledUsers enrolledUsers) {
        try {
            EnrollmentType type = EnrollmentType.valueOf(entity.getType());
            SessionStatus sessionStatus = new SessionStatus(ProgressStatus.valueOf(entity.getProgressStatus()));

            EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
                createEnrollmentCondition(type, entity),
                enrolledUsers,
                sessionStatus
            );

            return new Enrollment(entity.getId(), type, enrollmentPolicy);
        } catch (CanNotCreateException e) {
            throw new MappingException("Failed to map EnrollmentEntity to Enrollment", e);
        }
    }

    public static Enrollment toModel(EnrollmentEntity entity) {
        try {
            EnrollmentType type = EnrollmentType.valueOf(entity.getType());
            SessionStatus sessionStatus = new SessionStatus(ProgressStatus.valueOf(entity.getProgressStatus()));

            EnrollmentPolicy enrollmentPolicy = new EnrollmentPolicy(
                createEnrollmentCondition(type, entity),
                sessionStatus
            );

            return new Enrollment(entity.getId(), type, enrollmentPolicy);
        } catch (CanNotCreateException e) {
            throw new MappingException("Failed to map EnrollmentEntity to Enrollment", e);
        }
    }

    private static EnrollmentCondition createEnrollmentCondition(EnrollmentType type, EnrollmentEntity entity) {
        return type == EnrollmentType.FREE
            ? FreeEnrollmentCondition.INSTANCE
            : new PaidEnrollmentCondition(entity.getTuitionFee(), entity.getMaxEnrollment());
    }
    
    public static EnrollmentEntity toEntity(Long sessionId, Enrollment enrollment) {
        return toEntity(sessionId, null, enrollment);
    }
    
    public static EnrollmentEntity toEntity(Long sessionId, Long id, Enrollment enrollment) {
        EnrollmentPolicy policy = enrollment.getPolicy();
        return new EnrollmentEntity(
            sessionId,
            id,
            enrollment.getType().toString(),
            policy.getEnrollmentCondition().tuitionFee().orElse(0L),
            policy.getEnrollmentCondition().maxEnrollment().orElse(0),
            policy.getStatus().getSessionStatusType().toString(),
            policy.getStatus().getRecruitmentStatus().toString(),
            null,
            null
        );
    }
}
