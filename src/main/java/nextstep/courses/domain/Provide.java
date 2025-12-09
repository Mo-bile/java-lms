package nextstep.courses.domain;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.ProvideType;
import nextstep.payments.domain.Payment;

public class Provide {
    
    private final ProvideType type;
    private final ProvidePolicy policy;
    
    public Provide(ProvideType type) throws CanNotCreateException {
        this(type, new ProvidePolicy());
    }
    
    public Provide(ProvideType type, ProvidePolicy policy) throws CanNotCreateException {
        validate(type, policy);
        this.type = type;
        this.policy = policy;
    }
    
    private void validate(ProvideType type, ProvidePolicy policy) throws CanNotCreateException {
        if(policy.isNotCorrectBetween(type)) {
            throw new CanNotCreateException("강의타입과 정책이 일치하지 않습니다");
        }
    }
    
    public void applyPaid(int enrolledCount, Payment payment) throws CanNotJoinException {
        if(this.type == ProvideType.FREE) {
            throw new CanNotJoinException("유료 강의는 결제를 해야한다");
        }
        policy.isAvailableEnroll(enrolledCount);
        policy.isCorrectPay(payment);
    }
    
    public void applyFree() throws CanNotJoinException {
        if(this.type == ProvideType.PAID) {
            throw new CanNotJoinException("무료 강의는 결제할 수 없다");
        }
    }
    
    public boolean isPaid() {
        return this.type == ProvideType.PAID;
    }
}
