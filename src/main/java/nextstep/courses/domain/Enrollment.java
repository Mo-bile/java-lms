package nextstep.courses.domain;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.EnrollmentType;
import nextstep.payments.domain.Payment;

public class Enrollment {
    
    private final EnrollmentType type;
    private final EnrollmentPolicy policy;
    
    public Enrollment(EnrollmentType type) throws CanNotCreateException {
        this(type, new EnrollmentPolicy());
    }
    
    public Enrollment(EnrollmentType type, EnrollmentPolicy policy) throws CanNotCreateException {
        validate(type, policy);
        this.type = type;
        this.policy = policy;
    }
    
    private void validate(EnrollmentType type, EnrollmentPolicy policy) throws CanNotCreateException {
        if(policy.isNotCorrectBetween(type)) {
            throw new CanNotCreateException("강의타입과 정책이 일치하지 않습니다");
        }
    }
    
    public void applyPaid(Long userId, Payment payment) throws CanNotJoinException {
        if(this.type.isFree()) {
            throw new CanNotJoinException("유료 강의는 결제를 해야한다");
        }
        policy.validatePaidApply(userId, payment);
    }
    
    public void applyFree(Long userId) throws CanNotJoinException {
        if(this.type.isPaid()) {
            throw new CanNotJoinException("무료 강의는 결제할 수 없다");
        }
        policy.validateFreeApply(userId);
    }
    
    public boolean isPaid() {
        return this.type.isPaid();
    }
    
    public boolean isFree() {
        return this.type.isFree();
    }
}
