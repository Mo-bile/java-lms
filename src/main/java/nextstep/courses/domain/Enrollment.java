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
    
    public void apply(SessionApply sessionApply) throws CanNotJoinException {
        if(isFree()) {
            policy.validateFreeApply(sessionApply.getUserId());
            return;
        }
        policy.validatePaidApply(sessionApply);
    }
    
    public void apply(Long userid) throws CanNotJoinException {
        apply(new SessionApply(userid, null));
    }
    
    public void apply(Long userid, Payment payment) throws CanNotJoinException {
        apply(new SessionApply(userid, payment));
    }
    
    public boolean isPaid() {
        return this.type.isPaid();
    }
    
    public boolean isFree() {
        return this.type.isFree();
    }
}
