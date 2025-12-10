package nextstep.courses.domain;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.EnrollmentType;
import nextstep.payments.domain.Payment;

public class EnrollmentPolicy {
    
    private final Long tuitionFee;
    private final Integer maxEnrollment;
    private final EnrolledUsers enrolledUsers;
    private final SessionStatus status;
    
    public EnrollmentPolicy() throws CanNotCreateException {
        this(null, null, null, null);
    }
    
    public EnrollmentPolicy(Integer maxEnrollment, Long tuitionFee) throws CanNotCreateException {
        this(tuitionFee, maxEnrollment, new EnrolledUsers(), new SessionStatus());
    }
    
    public EnrollmentPolicy(EnrolledUsers enrolledUsers, SessionStatus status) throws CanNotCreateException {
        this(null, null, enrolledUsers, status);
    }
    
    public EnrollmentPolicy(Long tuitionFee, Integer maxEnrollment, EnrolledUsers enrolledUsers, SessionStatus status) throws CanNotCreateException {
        validate(maxEnrollment, tuitionFee);
        this.tuitionFee = tuitionFee;
        this.maxEnrollment = maxEnrollment;
        this.enrolledUsers = enrolledUsers;
        this.status = status;
    }
    
    private void validate(Integer maxEnrollment, Long tuitionFee) throws CanNotCreateException {
        if(IsMaxEnrollmentOnlyNull(maxEnrollment, tuitionFee)) {
            throw new CanNotCreateException("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
        }
        if(isTuitionFeeOnlyNull(maxEnrollment, tuitionFee)) {
            throw new CanNotCreateException("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
        }
    }
    
    private static boolean isTuitionFeeOnlyNull(Integer maxEnrollment, Long tuitionFee) {
        return maxEnrollment != null && tuitionFee == null;
    }
    
    private static boolean IsMaxEnrollmentOnlyNull(Integer maxEnrollment, Long tuitionFee) {
        return maxEnrollment == null && tuitionFee != null;
    }
    
    public void validatePaidApply(Long userId, Payment payment) throws CanNotJoinException {
        validateApplyStatus();
        validateEnrollment();
        validatePayment(payment);
        registerUser(userId);
    }
    
    public void validateFreeApply(Long userId) throws CanNotJoinException {
        validateApplyStatus();
        registerUser(userId);
    }
    
    public void validateApplyStatus() throws CanNotJoinException {
        status.isApplyStatus();
    }
    
    public void registerUser(Long userId) throws CanNotJoinException {
        enrolledUsers.registerUserId(userId);
    }
    
    public void validatePayment(Payment payment) throws CanNotJoinException {
        if(this.tuitionFee == null) {
            throw new CanNotJoinException("무료 강의는 지불할 수 없다");
        }
        if(payment.isNotSameAmount(this.tuitionFee)) {
            throw new CanNotJoinException("지불한 금액과 수강료 금액이 다르다");
        }
    }
    
    public void validateEnrollment() throws CanNotJoinException {
        if(this.maxEnrollment == null) {
            throw new CanNotJoinException("무료강의는 정원이 없다");
        }
        
        if(this.enrolledUsers.isAlreadyExceed(this.maxEnrollment)) {
            throw new CanNotJoinException("이미 정원을 초과했다");
        }
    }
    
    public boolean isNotCorrectBetween(EnrollmentType type) {
        if(type == EnrollmentType.FREE) {
            return !(this.maxEnrollment == null && this.tuitionFee == null);
        }
        if(type == EnrollmentType.PAID) {
            return !(this.maxEnrollment != null && this.tuitionFee != null);
        }
        return true;
    }
}