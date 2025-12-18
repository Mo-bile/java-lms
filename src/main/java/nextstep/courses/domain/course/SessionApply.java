package nextstep.courses.domain.course;

import nextstep.payments.domain.Payment;

public class SessionApply {
    
    private final long userId;
    private final Payment payment;
    
    public SessionApply(long userId, Payment payment) {
        this.userId = userId;
        this.payment = payment;
    }
    
    public long getUserId() {
        return userId;
    }
    
    public Payment getPayment() {
        return payment;
    }
}
