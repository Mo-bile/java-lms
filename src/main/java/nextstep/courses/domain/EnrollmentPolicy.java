package nextstep.courses.domain;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.enumerate.EnrollmentType;

public class EnrollmentPolicy {
    
    private final EnrollmentCondition enrollmentCondition;
    private final EnrolledUsers enrolledUsers;
    private final SessionStatus status;
    
    public EnrollmentPolicy(EnrollmentCondition enrollmentCondition) {
        this(enrollmentCondition, new EnrolledUsers(), new SessionStatus());
    }
    
    public EnrollmentPolicy(EnrollmentCondition enrollmentCondition, EnrolledUsers enrolledUsers, SessionStatus status) {
        this.enrollmentCondition = enrollmentCondition;
        this.enrolledUsers = enrolledUsers;
        this.status = status;
    }
    
    public void enroll(SessionApply sessionApply) throws CanNotJoinException {
        validateEnrollStatus();
        enrollmentCondition.isFull(enrolledUsers);
        enrollmentCondition.isPaid(sessionApply.getPayment());
        registerUser(sessionApply.getUserId());
    }
    
    private void validateEnrollStatus() throws CanNotJoinException {
        status.isApplyStatus();
    }
    
    private void registerUser(Long userId) throws CanNotJoinException {
        enrolledUsers.registerUserId(userId);
    }
    
    public boolean isNotCorrectBetween(EnrollmentType type) {
        if(type.isFree()) {
            return !(this.enrollmentCondition.maxEnrollment().isEmpty() && this.enrollmentCondition.tuitionFee().isEmpty());
        }
        if(type.isPaid()) {
            return !(this.enrollmentCondition.maxEnrollment().isPresent() && this.enrollmentCondition.tuitionFee().isPresent());
        }
        return true;
    }
}