package nextstep.courses.domain;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.ProvideType;

public class ProvidePolicy {
    
    private final Integer maxEnrollment;
    private final Integer tuitionFee;
    
    public ProvidePolicy() throws CanNotCreateException {
        this(null, null);
    }
    
    public ProvidePolicy(Integer maxEnrollment, Integer tuitionFee) throws CanNotCreateException {
        validate(maxEnrollment, tuitionFee);
        this.maxEnrollment = maxEnrollment;
        this.tuitionFee = tuitionFee;
    }
    
    private void validate(Integer maxEnrollment, Integer tuitionFee) throws CanNotCreateException {
        if(IsMaxEnrollmentOnlyNull(maxEnrollment, tuitionFee)) {
            throw new CanNotCreateException("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
        }
        if(isTuitionFeeOnlyNull(maxEnrollment, tuitionFee)) {
            throw new CanNotCreateException("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
        }
    }
    
    private static boolean isTuitionFeeOnlyNull(Integer maxEnrollment, Integer tuitionFee) {
        return maxEnrollment != null && tuitionFee == null;
    }
    
    private static boolean IsMaxEnrollmentOnlyNull(Integer maxEnrollment, Integer tuitionFee) {
        return maxEnrollment == null && tuitionFee != null;
    }
    
    public void isCorrectPay(int pay) throws CanNotJoinException {
        if(this.tuitionFee == null) {
            throw new CanNotJoinException("무료 강의는 지불할 수 없다");
        }
        if(this.tuitionFee != pay) {
            throw new CanNotJoinException("지불한 금액과 수강료 금액이 다르다");
        }
    }
    
    public void isAvailableEnroll(int enrolledCount) throws CanNotJoinException {
        if(this.maxEnrollment == null) {
            throw new CanNotJoinException("무료강의는 정원이 없다");
        }
        
        if(this.maxEnrollment <= enrolledCount) {
            throw new CanNotJoinException("이미 정원을 초과했다");
        }
    }
    
    public boolean isNotCorrectBetween(ProvideType type) {
        if(type == ProvideType.FREE) {
            return !(this.maxEnrollment == null && this.tuitionFee == null);
        }
        if(type == ProvideType.PAID) {
            return !(this.maxEnrollment != null && this.tuitionFee != null);
        }
        return true;
    }
}